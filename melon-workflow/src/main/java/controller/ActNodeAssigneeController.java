package controller;

import domain.ActNodeAssignee;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.annotation.RepeatSubmit;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.enums.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.IActNodeAssigneeService;

import javax.validation.constraints.NotBlank;

/**
 * @program: ruoyi-vue-plus
 * @description: 流程节点人员设置控制层
 * @author: gssong
 * @created: 2021/11/21 13:48
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/actNodeAssignee")
public class ActNodeAssigneeController extends BaseController {

    private final IActNodeAssigneeService iActNodeAssigneeService;

    /**
     * @Description: 保存流程节点人员设置
     * @param: actNodeAssignee
     * @return: com.ruoyi.common.core.domain.R<com.ruoyi.workflow.domain.ActNodeAssignee>
     * @Author: gssong
     * @Date: 2021/11/21
     */
    @Log(title = "流程节点人员设置管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody ActNodeAssignee actNodeAssignee) {
        return AjaxResult.success(iActNodeAssigneeService.add(actNodeAssignee));
    }

    /**
     * @Description: 按照流程定义id和流程节点id查询流程节点人员设置
     * @param: actNodeAssignee
     * @return: com.ruoyi.common.core.domain.R<com.ruoyi.workflow.domain.ActNodeAssignee>
     * @Author: gssong
     * @Date: 2021/11/21
     */
    @GetMapping("/{processDefinitionId}/{nodeId}")
    public AjaxResult getInfoSetting(@NotBlank(message = "流程定义id不能为空") @PathVariable String processDefinitionId,
                                     @NotBlank(message = "流程节点id不能为空") @PathVariable String nodeId) {
        ActNodeAssignee nodeAssignee = iActNodeAssigneeService.getInfoSetting(processDefinitionId, nodeId);
        return AjaxResult.success(nodeAssignee);
    }

    /**
     * @Description: 删除流程节点人员设置
     * @param: id
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @Author: gssong
     * @Date: 2021/11/21
     */
    @Log(title = "流程节点人员设置管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult del(@NotBlank(message = "主键不能为空") @PathVariable String id) {
        return toAjax(iActNodeAssigneeService.del(id) ? 1 : 0);
    }

    /**
     * @Description: 复制给最新流程节点人员设置
     * @param: id 流程定义id
     * @param: key 流程定义key
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @Author: gssong
     * @Date: 2022/03/26
     */
    @Log(title = "流程节点人员设置管理", businessType = BusinessType.INSERT)
    @PostMapping("/copy/{id}/{key}")
    public AjaxResult copy(@NotBlank(message = "id不能为空") @PathVariable("id") String id,
                           @NotBlank(message = "流程Key不能为空") @PathVariable("key") String key) {
        Boolean copy = iActNodeAssigneeService.copy(id, key);
        if (copy) {
            return AjaxResult.success();
        }
        return AjaxResult.error("当前流程未设置人员");
    }
}
