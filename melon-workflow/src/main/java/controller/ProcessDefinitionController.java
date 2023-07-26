package controller;


import domain.bo.DefinitionBo;
import domain.vo.ActProcessNodeVo;
import domain.vo.ProcessDefinitionVo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.R;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.enums.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.IProcessDefinitionService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: ruoyi-vue-plus
 * @description: 流程定义
 * @author: gssong
 * @created: 2021/10/07 11:12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/definition")
public class ProcessDefinitionController extends BaseController {

    private final IProcessDefinitionService iProcessDefinitionService;

    /**
     * @Description: 查询流程定义列表
     * @param: defReq
     * @return: com.ruoyi.common.core.domain.R<java.util.List < com.ruoyi.workflow.domain.vo.ProcessDefinitionVo>>
     * @Author: gssong
     * @Date: 2021/10/7
     */
    @GetMapping("/list")
    public TableDataInfo getByPage(DefinitionBo defReq) {
        return iProcessDefinitionService.getByPage(defReq);
    }

    /**
     * @Description: 查询历史流程定义列表
     * @param: definitionBo
     * @return: com.ruoyi.common.core.domain.R<java.util.List < com.ruoyi.workflow.domain.vo.ProcessDefinitionVo>>
     * @Author: gssong
     * @Date: 2021/10/7
     */
    @GetMapping("/hisList")
    public AjaxResult getHisByPage(DefinitionBo definitionBo) {
        List<ProcessDefinitionVo> definitionVoList = iProcessDefinitionService.getHisByPage(definitionBo);
        return AjaxResult.success(definitionVoList);
    }


    /**
     * @Description: 删除流程定义
     * @param: deploymentId 流程部署id
     * @param: definitionId 流程定义id
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @Author: gssong
     * @Date: 2021/10/7
     */
    @Log(title = "流程定义管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deploymentId}/{definitionId}")
    public AjaxResult deleteDeployment(@NotBlank(message = "流程部署id不能为空") @PathVariable String deploymentId,
                                       @NotBlank(message = "流程定义id不能为空") @PathVariable String definitionId) {
        return iProcessDefinitionService.deleteDeployment(deploymentId, definitionId);
    }


    /**
     * @Description: 通过zip或xml部署流程定义
     * @param: file
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/4/12 13:32
     */
    @Log(title = "流程定义管理", businessType = BusinessType.INSERT)
    @PostMapping("/deployByFile")
    public AjaxResult deployByFile(@RequestParam("file") MultipartFile file) {
        return iProcessDefinitionService.deployByFile(file);
    }


    /**
     * @Description: 导出流程定义文件（xml,png)
     * @param: type 类型 xml 或 png
     * @param: definitionId 流程定义id
     * @param: response
     * @return: void
     * @Author: gssong
     * @Date: 2021/10/7
     */
    @GetMapping("/export/{type}/{definitionId}")
    public void exportFile(@NotBlank(message = "文件类型不能为空") @PathVariable String type,
                           @NotBlank(message = "流程定义id不能为空") @PathVariable String definitionId,
                           HttpServletResponse response) {
        iProcessDefinitionService.exportFile(type, definitionId, response);
    }

    /**
     * @Description: 查看xml文件
     * @param: definitionId
     * @return: com.ruoyi.common.core.domain.R<java.lang.String>
     * @author: gssong
     * @Date: 2022/5/3 19:25
     */
    @GetMapping("/getXml/{definitionId}")
    public AjaxResult getXml(@NotBlank(message = "流程定义id不能为空") @PathVariable String definitionId) {
        String       xmlStr = iProcessDefinitionService.getXml(definitionId);
        List<String> xml    = new ArrayList<>(Arrays.asList(xmlStr.split("\n")));
        return AjaxResult.success("操作成功", xml);
    }

    /**
     * @Description: 激活或者挂起流程定义
     * @param: data 参数
     * @return: com.ruoyi.common.core.domain.R<java.lang.Boolean>
     * @Author: gssong
     * @Date: 2021/10/10
     */
    @Log(title = "流程定义管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateProcDefState")
    public AjaxResult updateProcDefState(@RequestBody Map<String, Object> data) {
        return AjaxResult.success(iProcessDefinitionService.updateProcDefState(data));
    }

    /**
     * @Description: 查询流程环节
     * @param: processDefinitionId
     * @return: com.ruoyi.common.core.domain.R<java.util.List < com.ruoyi.workflow.domain.vo.ActProcessNodeVo>>
     * @author: gssong
     * @Date: 2021/11/19
     */
    @GetMapping("/setting/{processDefinitionId}")
    public AjaxResult setting(@NotBlank(message = "流程定义id不能为空") @PathVariable String processDefinitionId) {
        return AjaxResult.success(iProcessDefinitionService.setting(processDefinitionId));
    }

}
