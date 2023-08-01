package fun.sssdnsy.controller;

import fun.sssdnsy.domain.bo.ModelBo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.annotation.RepeatSubmit;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.core.validate.EditGroup;
import fun.sssdnsy.enums.BusinessType;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.RepositoryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fun.sssdnsy.service.IModelService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @program: ruoyi-vue-plus
 * @description: 模型控制器
 * @author: gssong
 * @created: 2022-02-26
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/model")
public class ModelController extends BaseController {

    private final IModelService iModelService;

    private final RepositoryService repositoryService;

    /**
     * @Description: 保存模型
     */
    @Log(title = "模型管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult saveModelXml(@Validated(EditGroup.class) @RequestBody ModelBo data) {
        return iModelService.saveModelXml(data);
    }

    /**
     * @Description: 查询模型信息
     */
    @GetMapping("/getInfo/{modelId}/xml")
    public AjaxResult getEditorXml(@NotBlank(message = "模型id不能为空") @PathVariable String modelId) {
        return iModelService.getEditorXml(modelId);
    }

    /**
     * @Description: 查询模型列表
     */
    @GetMapping("/list")
    public TableDataInfo getByPage(ModelBo modelBo) {
        return iModelService.getByPage(modelBo);
    }

    /**
     * @Description: 新建模型
     */
    @ApiOperation("新建模型")
    @Log(title = "模型管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody ModelBo data) {
        return iModelService.add(data);
    }

    /**
     * @Description: 通过流程定义模型id部署流程定义
     */
    @Log(title = "模型管理", businessType = BusinessType.INSERT)
    @PostMapping("/deploy/{id}")
    public AjaxResult deploy(@NotBlank(message = "流程部署id不能为空") @PathVariable("id") String id) {
        return iModelService.deploy(id);
    }

    /**
     * @Description: 删除流程定义模型
     */
    @Log(title = "模型管理", businessType = BusinessType.DELETE)
    @RepeatSubmit()
    @DeleteMapping("/{ids}")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult add(@NotEmpty(message = "主键不能为空") @PathVariable String[] ids) {
        for (String id : ids) {
            repositoryService.deleteModel(id);
        }
        return AjaxResult.success();
    }

    /**
     * @Description: 导出流程定义模型zip压缩包
     */
    @GetMapping("/export/zip/{modelId}")
    public void exportZip(@NotEmpty(message = "模型id不能为空") @PathVariable String modelId,
                          HttpServletResponse response) {
        iModelService.exportZip(modelId, response);
    }

    /**
     * @Description: 将流程定义转换为模型
     */
    @Log(title = "模型管理", businessType = BusinessType.UPDATE)
    @PutMapping("/convertToModel/{processDefinitionId}")
    public AjaxResult convertToModel(@NotEmpty(message = "流程定义id不能为空") @PathVariable String processDefinitionId) {
        Boolean convertToModel = iModelService.convertToModel(processDefinitionId);
        return convertToModel ? AjaxResult.success() : AjaxResult.error();
    }

}
