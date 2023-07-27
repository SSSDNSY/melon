package fun.sssdnsy.service;


import fun.sssdnsy.domain.ActTaskNode;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.bo.*;

import java.util.List;
import java.util.Map;

/**
 * @program: ruoyi-vue-plus
 * @description: 任务接口
 * @author: gssong
 * @created: 2021/10/17 14:57
 */
public interface ITaskService {
    /**
     * 查询当前用户的待办任务
     *
     * @param req
     * @return
     */
    TableDataInfo getTaskWaitByPage(TaskBo req);

    /**
     * 完成任务
     *
     * @param req
     * @return
     */
    Boolean completeTask(TaskCompleteBo req);

    /**
     * 查询当前用户的已办任务
     *
     * @param req
     * @return
     */
    TableDataInfo getTaskFinishByPage(TaskBo req);

    /**
     * 获取目标节点（下一个节点）
     *
     * @param req
     * @return
     */
    Map<String, Object> getNextNodeInfo(NextNodeBo req);

    /**
     * 查询所有用户的已办任务
     *
     * @param req
     * @return
     */
    TableDataInfo getAllTaskFinishByPage(TaskBo req);

    /**
     * 查询所有用户的待办任务
     *
     * @param req
     * @return
     */
    TableDataInfo getAllTaskWaitByPage(TaskBo req);

    /**
     * 驳回审批
     *
     * @param backProcessBo
     * @return
     */
    String backProcess(BackProcessBo backProcessBo);

    /**
     * 获取历史任务节点，用于驳回功能
     *
     * @param processInstId
     * @return
     */
    List<ActTaskNode> getBackNodes(String processInstId);

    /**
     * 委托
     *
     * @param delegateBo
     * @return
     */
    Boolean delegateTask(DelegateBo delegateBo);

    /**
     * 转办任务
     *
     * @param transmitBo
     * @return
     */
    AjaxResult transmitTask(TransmitBo transmitBo);

    /**
     * 会签任务加签
     *
     * @param addMultiBo
     * @return
     */
    AjaxResult addMultiInstanceExecution(AddMultiBo addMultiBo);

    /**
     * 会签任务减签
     *
     * @param deleteMultiBo
     * @return
     */
    AjaxResult deleteMultiInstanceExecution(DeleteMultiBo deleteMultiBo);

    /**
     * 修改办理人
     *
     * @param updateAssigneeBo
     * @return
     */
    AjaxResult updateAssignee(UpdateAssigneeBo updateAssigneeBo);


    /**
     * 查询流程变量
     *
     * @param taskId
     * @return
     */
    AjaxResult getProcessInstVariable(String taskId);

    /**
     * 修改审批意见
     *
     * @param commentId
     * @param comment
     * @return
     */
    AjaxResult editComment(String commentId, String comment);
}
