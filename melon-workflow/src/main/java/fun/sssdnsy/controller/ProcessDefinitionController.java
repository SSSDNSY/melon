package fun.sssdnsy.controller;


import fun.sssdnsy.domain.bo.DefinitionBo;
import fun.sssdnsy.domain.vo.ProcessDefinitionVo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.enums.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import fun.sssdnsy.service.IProcessDefinitionService;

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
     *  查询流程定义列表
     */
    @GetMapping("/list")
    public TableDataInfo getByPage(DefinitionBo defReq) {
        return iProcessDefinitionService.getByPage(defReq);
    }

    /**
     * @Description: 查询历史流程定义列表
     */
    @GetMapping("/hisList")
    public AjaxResult getHisByPage(DefinitionBo definitionBo) {
        List<ProcessDefinitionVo> definitionVoList = iProcessDefinitionService.getHisByPage(definitionBo);
        return AjaxResult.success(definitionVoList);
    }


    /**
     * @Description: 删除流程定义
     */
    @Log(title = "流程定义管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deploymentId}/{definitionId}")
    public AjaxResult deleteDeployment(@NotBlank(message = "流程部署id不能为空") @PathVariable String deploymentId,
                                       @NotBlank(message = "流程定义id不能为空") @PathVariable String definitionId) {
        return iProcessDefinitionService.deleteDeployment(deploymentId, definitionId);
    }


    /**
     * @Description: 通过zip或xml部署流程定义
     */
    @Log(title = "流程定义管理", businessType = BusinessType.INSERT)
    @PostMapping("/deployByFile")
    public AjaxResult deployByFile(@RequestParam("file") MultipartFile file) {
        return iProcessDefinitionService.deployByFile(file);
    }


    /**
     * @Description: 导出流程定义文件（xml,png)
     */
    @GetMapping("/export/{type}/{definitionId}")
    public void exportFile(@NotBlank(message = "文件类型不能为空") @PathVariable String type,
                           @NotBlank(message = "流程定义id不能为空") @PathVariable String definitionId,
                           HttpServletResponse response) {
        iProcessDefinitionService.exportFile(type, definitionId, response);
    }

    /**
     * @Description: 查看xml文件
     */
    @GetMapping("/getXml/{definitionId}")
    public AjaxResult getXml(@NotBlank(message = "流程定义id不能为空") @PathVariable String definitionId) {
        String       xmlStr = iProcessDefinitionService.getXml(definitionId);
        List<String> xml    = new ArrayList<>(Arrays.asList(xmlStr.split("\n")));
        return AjaxResult.success("操作成功", xml);
    }

    /**
     * @Description: 激活或者挂起流程定义
     */
    @Log(title = "流程定义管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateProcDefState")
    public AjaxResult updateProcDefState(@RequestBody Map<String, Object> data) {
        return AjaxResult.success(iProcessDefinitionService.updateProcDefState(data));
    }

    /**
     * @Description: 查询流程环节
     */
    @GetMapping("/setting/{processDefinitionId}")
    public AjaxResult setting(@NotBlank(message = "流程定义id不能为空") @PathVariable String processDefinitionId) {
        return AjaxResult.success(iProcessDefinitionService.setting(processDefinitionId));
    }

}
