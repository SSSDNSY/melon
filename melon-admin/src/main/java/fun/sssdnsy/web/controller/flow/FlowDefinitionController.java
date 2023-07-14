package fun.sssdnsy.web.controller.flow;

import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.FlowDefinition;
import fun.sssdnsy.service.IFlowDefinitionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Desc 流程定义控制器
 * @Author pengzh
 * @Since 2023-07-13
 */
@RestController
@RequestMapping("/flowDefinition")
public class FlowDefinitionController extends BaseController {

    @Resource
    private IFlowDefinitionService flowDefinitionService;

    /**
     * 获取流程定义
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody FlowDefinition flowDefinition) {
        return flowDefinitionService.list(flowDefinition);
    }

    /**
     * 获取流程定义
     */
    @GetMapping("/get")
    public AjaxResult get(@RequestParam("processKey") String processKey) {
        return AjaxResult.success(flowDefinitionService.get(processKey));
    }

    /**
     * 保存流程定义
     */
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowDefinition flowDefinition) {
        return AjaxResult.success(flowDefinitionService.save(flowDefinition));
    }

    /**
     * 删除流程定义
     */
    @DeleteMapping(value = "/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return AjaxResult.success(flowDefinitionService.remove(ids));
    }

    /**
     * 流程发布
     */
    @PostMapping(value = "/deployWithBPMNJS")
    public AjaxResult deployWithBPMNJS(@RequestParam String stringBPMNXml) {
        flowDefinitionService.deployWithBPMNJS(stringBPMNXml);
        return AjaxResult.success();
    }

    /**
     * 获取流程定义的xml模版
     */
    @GetMapping(value = "/getDeploymentXmlById/{deploymentId}")
    public void getDeploymentXmlById(HttpServletResponse response, @PathVariable String deploymentId) throws IOException {
        flowDefinitionService.getResourceAsStream(response, deploymentId, "leave.bpmn");
    }
}
