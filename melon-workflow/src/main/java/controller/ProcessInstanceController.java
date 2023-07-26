package controller;

import domain.bo.ProcessInstFinishBo;
import domain.bo.ProcessInstRunningBo;
import domain.bo.StartProcessBo;
import domain.vo.ActHistoryInfoVo;
import domain.vo.ProcessInstFinishVo;
import domain.vo.ProcessInstRunningVo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.annotation.RepeatSubmit;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.R;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.enums.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.IProcessInstanceService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @program: ruoyi-vue-plus
 * @description: 流程实例
 * @author: gssong
 * @created: 2021/10/10 18:36
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/processInstance")
public class ProcessInstanceController {

    private final IProcessInstanceService processInstanceService;

    /**
     * @Description: 提交申请，启动流程实例
     * @param: startProcessBo
     * @return: com.ruoyi.common.core.domain.R<java.util.Map < java.lang.String, java.lang.Object>>
     * @author: gssong
     * @Date: 2021/10/10
     */
    @Log(title = "流程实例管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/startWorkFlow")
    public AjaxResult startWorkFlow(@RequestBody StartProcessBo startProcessBo) {
        Map<String, Object> map = processInstanceService.startWorkFlow(startProcessBo);
        return AjaxResult.success("提交成功", map);
    }


    /**
     * @Description: 通过流程实例id查询流程审批记录
     * @param: processInstanceId
     * @return: com.ruoyi.common.core.domain.R<java.util.List < com.ruoyi.workflow.domain.vo.ActHistoryInfoVo>>
     * @Author: gssong
     * @Date: 2021/10/16
     */
    @GetMapping("/getHistoryInfoList/{processInstanceId}")
    public AjaxResult getHistoryInfoList(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstanceId) {
        List<ActHistoryInfoVo> historyInfoList = processInstanceService.getHistoryInfoList(processInstanceId);
        return AjaxResult.success(historyInfoList);
    }

    /**
     * @Description: 通过流程实例id获取历史流程图
     * @param: processInstId
     * @param: response
     * @return: void
     * @Author: gssong
     * @Date: 2021/10/16
     */
    
    @GetMapping("/getHistoryProcessImage")
    public void getHistoryProcessImage(@NotBlank(message = "流程实例id不能为空") @RequestParam String processInstanceId,
                                       HttpServletResponse response) {
        processInstanceService.getHistoryProcessImage(processInstanceId, response);
    }

    /**
     * @Description: 查询正在运行的流程实例
     * @param: req
     * @return: com.ruoyi.common.core.page.TableDataInfo<com.ruoyi.workflow.domain.vo.ProcessInstRunningVo>
     * @Author: gssong
     * @Date: 2021/10/16
     */
    @GetMapping("/getProcessInstRunningByPage")
    public TableDataInfo getProcessInstRunningByPage(ProcessInstRunningBo req) {
        return processInstanceService.getProcessInstRunningByPage(req);
    }

    /**
     * @Description: 挂起或激活流程实例
     * @param: data
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @Author: gssong
     * @Date: 2021/10/16
     */
    @Log(title = "流程实例管理", businessType = BusinessType.UPDATE)
    @PutMapping("/state")
    public AjaxResult updateProcInstState(@RequestBody Map<String, Object> data) {
        try {
            processInstanceService.updateProcInstState(data);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    /**
     * @Description: 作废流程实例，不会删除历史记录
     * @param: processInstId
     * @return: com.ruoyi.common.core.domain.R<java.lang.Boolean>
     * @Author: gssong
     * @Date: 2021/10/16
     */
    @Log(title = "流程实例管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteRuntimeProcessInst/{processInstId}")
    public AjaxResult deleteRuntimeProcessInst(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstId) {
        boolean b = processInstanceService.deleteRuntimeProcessInst(processInstId);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * @Description: 运行中的实例 删除程实例，删除历史记录，删除业务与流程关联信息
     * @param: processInstId
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @Author: gssong
     * @Date: 2021/10/16
     */
    @Log(title = "流程实例管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteRuntimeProcessAndHisInst/{processInstId}")
    public AjaxResult deleteRuntimeProcessAndHisInst(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstId) {
        boolean b = processInstanceService.deleteRuntimeProcessAndHisInst(processInstId);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * @Description: 已完成的实例 删除程实例，删除历史记录，删除业务与流程关联信息
     * @param: processInstId
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @Author: gssong
     * @Date: 2021/10/16
     */
    @Log(title = "流程实例管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteFinishProcessAndHisInst/{processInstId}")
    public AjaxResult deleteFinishProcessAndHisInst(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstId) {
        boolean b = processInstanceService.deleteFinishProcessAndHisInst(processInstId);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * @param processInstId
     * @Description: 撤销申请
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/1/21
     */
    @Log(title = "流程实例管理", businessType = BusinessType.INSERT)
    @GetMapping("/cancelProcessApply/{processInstId}")
    public AjaxResult cancelProcessApply(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstId) {
        boolean b = processInstanceService.cancelProcessApply(processInstId);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * @Description: 查询已结束的流程实例
     * @param: req
     * @return: com.ruoyi.common.core.page.TableDataInfo<com.ruoyi.workflow.domain.vo.ProcessInstFinishVo>
     * @Author: gssong
     * @Date: 2021/10/23
     */
    @GetMapping("/getProcessInstFinishByPage")
    public TableDataInfo getProcessInstFinishByPage(ProcessInstFinishBo req) {
        return processInstanceService.getProcessInstFinishByPage(req);
    }

}