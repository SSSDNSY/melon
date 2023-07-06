package fun.sssdnsy.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fun.sssdnsy.constant.HttpStatus;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.PageDomain;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.core.page.TableSupport;
import fun.sssdnsy.domain.ModelParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Api(value = "模型管理接口")
@RestController
@RequestMapping("/model")
public class ModelManageController extends BaseController {

    @Resource
    RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    @ApiOperation("查询所有模型")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(required = false) String key, @RequestParam(required = false) String name,
                              Integer pageSize, Integer pageNum) {
        ModelQuery query = repositoryService.createModelQuery();
        if (StringUtils.isNotEmpty(key)) {
            query.modelKey(key);
        }
        if (StringUtils.isNotEmpty(name)) {
            query.modelName(name);
        }
        PageDomain    pageDomain = TableSupport.buildPageRequest();
        int           start      = (pageNum - 1) * pageSize;
        List<Model>   page       = query.orderByCreateTime().desc().listPage(start, pageSize);
        TableDataInfo rspData    = new TableDataInfo();
        rspData.setMsg(MSG);
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(page);
        rspData.setTotal(query.list().size());
        return rspData;
    }

    /**
     * 新增模型
     */
    @ApiOperation("新增模型")
    @PostMapping("/add")
    public AjaxResult add(ModelParam modelRequest) throws JsonProcessingException {
        Model model = repositoryService.newModel();
        model.setCategory(modelRequest.getCategory());
        model.setKey(modelRequest.getKey());
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, modelRequest.getName());
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, modelRequest.getDescription());
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, modelRequest.getVersion());
        model.setMetaInfo(modelNode.toString());
        model.setName(modelRequest.getName());
        model.setVersion(modelRequest.getVersion());
        ModelQuery  modelQuery = repositoryService.createModelQuery();
        List<Model> list       = modelQuery.modelKey(modelRequest.getKey()).list();
        if (list.size() > 0) {
            return AjaxResult.error("模型标识不能重复");
        } else {
            // 保存模型到act_re_model表
            repositoryService.saveModel(model);
            HashMap<String, Object> content = new HashMap();
            content.put("resourceId", model.getId());
            HashMap<String, String> properties = new HashMap();
            properties.put("process_id", modelRequest.getKey());
            properties.put("name", modelRequest.getName());
            properties.put("category", modelRequest.getCategory());
            content.put("properties", properties);
            HashMap<String, String> stencilset = new HashMap();
            stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            content.put("stencilset", stencilset);
            // 保存模型文件到act_ge_bytearray表
            repositoryService.addModelEditorSource(model.getId(), objectMapper.writeValueAsBytes(content));
            return AjaxResult.success(model);
        }
    }

    @ApiOperation("部署模型")
    @GetMapping("/deploy/{modelId}")
    public AjaxResult deploy(@PathVariable String modelId) {
        try {
            Model     model     = repositoryService.getModel(modelId);
            byte[]    modelData = repositoryService.getModelEditorSource(modelId);
            JsonNode  jsonNode  = objectMapper.readTree(modelData);
            BpmnModel bpmnModel = (new BpmnJsonConverter()).convertToBpmnModel(jsonNode);
            Deployment deploy = repositoryService.createDeployment().category(model.getCategory())
                    .name(model.getName()).key(model.getKey())
                    .addBpmnModel(model.getKey() + ".bpmn20.xml", bpmnModel)
                    .deploy();
            model.setDeploymentId(deploy.getId());
            repositoryService.saveModel(model);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("流程图不合规范，请重新设计");
        }
    }


    @ApiOperation("删除模型")
    @PostMapping("/remove/{modelId}")
    public AjaxResult remove(@PathVariable String modelId) {
        repositoryService.deleteModel(modelId);
        return AjaxResult.success("删除成功");
    }


    @ApiOperation("导出模型")
    @GetMapping("/export/{modelId}")
    public void export(@PathVariable String modelId, HttpServletResponse response) throws IOException {
        byte[]               modelData = repositoryService.getModelEditorSource(modelId);
        JsonNode             jsonNode  = objectMapper.readTree(modelData);
        BpmnModel            bpmnModel = (new BpmnJsonConverter()).convertToBpmnModel(jsonNode);
        byte[]               xmlBytes  = (new BpmnXMLConverter()).convertToXML(bpmnModel, "UTF-8");
        ByteArrayInputStream in        = new ByteArrayInputStream(xmlBytes);
        IOUtils.copy(in, response.getOutputStream());
        String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setHeader("content-Type", "application/xml");
        response.flushBuffer();
    }

    @ApiOperation("打开在线编辑器时加载指定模型到页面")
    @RequestMapping(value = "/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        ObjectNode modelNode = null;

        Model model = repositoryService.getModel(modelId);

        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(ModelDataJsonConstants.MODEL_NAME, model.getName());
                }
                modelNode.put(ModelDataJsonConstants.MODEL_ID, model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
                        new String(repositoryService.getModelEditorSource(model.getId()), StandardCharsets.UTF_8));
                modelNode.put("model", editorJsonNode);

            } catch (Exception e) {
                logger.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return modelNode;
    }


    @ApiOperation("保存流程图编辑器的信息")
    @RequestMapping(value = "/{modelId}/save", method = RequestMethod.PUT)
    @ResponseStatus(value = org.springframework.http.HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, String name, String description, String json_xml, String svg_xml) {
        try {
            Model      model     = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            model.setDeploymentId(null);
            Integer version = model.getVersion();
            version++;
            model.setVersion(version);
            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes(StandardCharsets.UTF_8));
            InputStream     svgStream  = new ByteArrayInputStream(svg_xml.getBytes(StandardCharsets.UTF_8));
            TranscoderInput input      = new TranscoderInput(svgStream);
            PNGTranscoder   transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput      output    = new TranscoderOutput(outStream);
            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();
        } catch (Exception e) {
            logger.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
    }


}
