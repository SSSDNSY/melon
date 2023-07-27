package fun.sssdnsy.controller;

import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.domain.bo.*;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.TaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fun.sssdnsy.service.ITaskService;

import javax.validation.constraints.NotBlank;

/**
 * @program: ruoyi-vue-plus
 * @description: 任务管理控制器
 * @author: gssong
 * @created: 2021/10/17 14:46
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/task")
public class TaskController extends BaseController {

    private final ITaskService iTaskService;

    private final TaskService taskService;

    /**
     * @Description: 查询当前用户的待办任务
     * @param: req
     * @return: com.ruoyi.common.core.page.TableDataInfo<com.ruoyi.workflow.TaskWaitingVo>
     * @Author: gssong
     * @Date: 2021/10/17
     */
    @GetMapping("/getTaskWaitByPage")
    public TableDataInfo getTaskWaitByPage(TaskBo req) {
        return iTaskService.getTaskWaitByPage(req);
    }


    /**
     * @Description: 查询当前用户的已办任务
     * @param: req
     * @return: com.ruoyi.common.core.page.TableDataInfo<com.ruoyi.workflow.TaskWaitingVo>
     * @Author: gssong
     * @Date: 2021/10/23
     */
    @GetMapping("/getTaskFinishByPage")
    public TableDataInfo getTaskFinishByPage(TaskBo req) {
        return iTaskService.getTaskFinishByPage(req);
    }

    /**
     * @Description: 查询所有用户的已办任务
     * @param: req
     * @return: com.ruoyi.common.core.page.TableDataInfo<com.ruoyi.workflow.TaskWaitingVo>
     * @Author: gssong
     * @Date: 2021/10/23
     */
    @GetMapping("/getAllTaskFinishByPage")
    public TableDataInfo getAllTaskFinishByPage(TaskBo req) {
        return iTaskService.getAllTaskFinishByPage(req);
    }


    /**
     * @Description: 查询所有用户的待办任务
     * @param: req
     * @return: com.ruoyi.common.core.page.TableDataInfo<com.ruoyi.workflow.TaskWaitingVo>
     * @Author: gssong
     * @Date: 2021/10/17
     */
    @GetMapping("/getAllTaskWaitByPage")
    public TableDataInfo getAllTaskWaitByPage(TaskBo req) {
        return iTaskService.getAllTaskWaitByPage(req);
    }

    /**
     * @Description: 完成任务
     * @param: req
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2021/10/21 13:34
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/completeTask")
    public AjaxResult completeTask(@RequestBody TaskCompleteBo req) {
        Boolean task = iTaskService.completeTask(req);
        if (!task) {
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * @Description: 获取目标节点（下一个节点）
     * @param: taskId
     * @return: com.ruoyi.common.core.domain.R<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: gssong
     * @Date: 2021/10/23
     */
    @PostMapping("/getNextNodeInfo")
    public AjaxResult getNextNodeInfo(@RequestBody NextNodeBo req) {
        return AjaxResult.success(iTaskService.getNextNodeInfo(req));
    }

    /**
     * @Description: 驳回审批
     * @param: backProcessBo
     * @return: com.ruoyi.common.core.domain.R<java.lang.String>
     * @Author: gssong
     * @Date: 2021/11/6
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/backProcess")
    public AjaxResult backProcess(@RequestBody BackProcessBo backProcessBo) {
        return AjaxResult.success(iTaskService.backProcess(backProcessBo));
    }

    /**
     * @Description: 获取历史任务节点，用于驳回功能
     * @param: processInstId
     * @return: com.ruoyi.common.core.domain.R<java.util.List < com.ruoyi.workflow.ActTaskNode>>
     * @Author: gssong
     * @Date: 2021/11/6
     */
    @GetMapping("/getBackNodes/{processInstId}")
    public AjaxResult getBackNodes(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstId) {
        return AjaxResult.success(iTaskService.getBackNodes(processInstId));
    }

    /**
     * @Description: 签收（拾取）任务
     * @param: taskId
     * @return: @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @Author: gssong
     * @Date: 2021/11/16
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/claim/{taskId}")
    public AjaxResult claimTask(@NotBlank(message = "任务id不能为空") @PathVariable String taskId) {
        try {
            taskService.claim(taskId, SecurityUtils.getUserId().toString());
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("签收任务失败");
        }
    }

    /**
     * @Description: 归还（拾取的）任务
     * @param: taskId
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/01/01
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/returnTask/{taskId}")
    public AjaxResult returnTask(@NotBlank(message = "任务id不能为空") @PathVariable String taskId) {
        try {
            taskService.setAssignee(taskId, null);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("归还任务失败");
        }
    }

    /**
     * @Description: 委派任务
     * @param: delegateBo
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/3/4 13:18
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/delegateTask")
    public AjaxResult delegateTask(@Validated({AddGroup.class}) @RequestBody DelegateBo delegateBo) {
        return toAjax(iTaskService.delegateTask(delegateBo));
    }

    /**
     * @Description: 转办任务
     * @param: transmitBo
     * @return: com.ruoyi.common.core.domain.R<java.lang.Boolean>
     * @author: gssong
     * @Date: 2022/3/13 13:18
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/transmitTask")
    public AjaxResult transmit(@Validated({AddGroup.class}) @RequestBody TransmitBo transmitBo) {
        return iTaskService.transmitTask(transmitBo);
    }

    /**
     * @Description: 会签任务加签
     * @param: addMultiBo
     * @return: com.ruoyi.common.core.domain.R<java.lang.Boolean>
     * @author: gssong
     * @Date: 2022/4/15 13:06
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/addMultiInstanceExecution")
    public AjaxResult addMultiInstanceExecution(@Validated({AddGroup.class}) @RequestBody AddMultiBo addMultiBo) {
        return iTaskService.addMultiInstanceExecution(addMultiBo);
    }

    /**
     * @Description: 会签任务减签
     * @param: deleteMultiBo
     * @return: com.ruoyi.common.core.domain.R<java.lang.Boolean>
     * @author: gssong
     * @Date: 2022/4/16 10:59
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/deleteMultiInstanceExecution")
    public AjaxResult deleteMultiInstanceExecution(@Validated({AddGroup.class}) @RequestBody DeleteMultiBo deleteMultiBo) {
        return iTaskService.deleteMultiInstanceExecution(deleteMultiBo);
    }

    /**
     * @Description: 修改办理人
     * @param: updateAssigneeBo
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/7/17 13:31
     */
    @Log(title = "任务管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAssignee")
    public AjaxResult updateAssignee(@Validated({AddGroup.class}) @RequestBody UpdateAssigneeBo updateAssigneeBo) {
        return iTaskService.updateAssignee(updateAssigneeBo);
    }

    /**
     * @Description: 查询流程变量
     * @param: taskId
     * @return: com.ruoyi.common.core.domain.R<java.util.List < com.ruoyi.workflow.VariableVo>>
     * @author: gssong
     * @Date: 2022/7/23 14:33
     */
    @GetMapping("/getProcessInstVariable/{taskId}")
    public AjaxResult getProcessInstVariable(@PathVariable String taskId) {
        return iTaskService.getProcessInstVariable(taskId);
    }

    /**
     * @Description: 修改审批意见
     * @param: commentId
     * @param: comment
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/7/24 13:38
     */
    @PutMapping("/editComment/{commentId}/{comment}")
    public AjaxResult editComment(@PathVariable String commentId, @PathVariable String comment) {
        return iTaskService.editComment(commentId, comment);
    }

}




