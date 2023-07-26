package service;

import domain.bo.DefinitionBo;
import domain.vo.ActProcessNodeVo;
import domain.vo.ProcessDefinitionVo;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.R;
import fun.sssdnsy.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author gssong
 */
public interface IProcessDefinitionService {
    /**
     * 查询流程定义列表
     *
     * @param defReq
     * @return
     */
    TableDataInfo getByPage(DefinitionBo defReq);

    /**
     * 删除流程定义
     *
     * @param deploymentId
     * @param definitionId
     * @return
     */
    AjaxResult deleteDeployment(String deploymentId, String definitionId);

    /**
     * 通过zip或xml部署流程定义
     *
     * @param file
     * @return
     */
    AjaxResult deployByFile(MultipartFile file);

    /**
     * 导出流程定义文件（xml,png)
     *
     * @param type
     * @param definitionId
     * @param response
     */
    void exportFile(String type, String definitionId, HttpServletResponse response);

    /**
     * 查看xml文件
     *
     * @param definitionId
     * @return
     */
    String getXml(String definitionId);

    /**
     * 查询历史流程定义列表
     *
     * @param defReq
     * @return
     */
    List<ProcessDefinitionVo> getHisByPage(DefinitionBo defReq);

    /**
     * 激活或者挂起流程定义
     *
     * @param data
     * @return
     */
    Boolean updateProcDefState(Map<String, Object> data);

    /**
     * 查询流程环节
     *
     * @param processDefinitionId
     * @return
     */
    List<ActProcessNodeVo> setting(String processDefinitionId);
}
