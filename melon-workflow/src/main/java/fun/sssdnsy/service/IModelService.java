package fun.sssdnsy.service;

import fun.sssdnsy.domain.bo.ModelBo;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: ruoyi-vue-plus
 * @description: 模型接口
 * @author: gssong
 * @created: 2022-02-26
 */
public interface IModelService {


    /**
     * 保存模型
     *
     * @param data
     * @return
     */
    AjaxResult saveModelXml(ModelBo data);

    /**
     * 查询模型信息
     *
     * @param modelId
     * @return
     */
    AjaxResult getEditorXml(String modelId);

    /**
     * 分页查询模型
     *
     * @param modelBo
     * @return
     */
    TableDataInfo getByPage(ModelBo modelBo);

    /**
     * 新增模型对象
     *
     * @param data
     * @return
     */
    AjaxResult add(ModelBo data);

    /**
     * 通过流程定义模型id部署流程定义
     *
     * @param id
     * @return
     */
    AjaxResult deploy(String id);

    /**
     * 导出流程定义模型zip压缩包
     *
     * @param modelId
     * @param response
     */
    void exportZip(String modelId, HttpServletResponse response);

    /**
     * 将流程定义转换为模型
     *
     * @param processDefinitionId
     * @return
     */
    Boolean convertToModel(String processDefinitionId);
}
