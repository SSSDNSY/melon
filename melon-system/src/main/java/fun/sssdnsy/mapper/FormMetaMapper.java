package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.FormMeta;

import java.util.List;

/**
 * 表单Mapper接口
 */
public interface FormMetaMapper {
    /**
     * 查询表单
     *
     * @param id 表单主键
     * @return 表单
     */
    public FormMeta selectFormMetaById(String id);

    /**
     * 查询表单列表
     *
     * @param formMeta 表单
     * @return 表单集合
     */
    public List<FormMeta> selectFormMetaList(FormMeta formMeta);

    /**
     * 新增表单
     *
     * @param formMeta 表单
     * @return 结果
     */
    public int insertFormMeta(FormMeta formMeta);

    /**
     * 修改表单
     *
     * @param formMeta 表单
     * @return 结果
     */
    public int updateFormMeta(FormMeta formMeta);

    /**
     * 删除表单
     *
     * @param id 表单主键
     * @return 结果
     */
    public int deleteFormMetaById(String id);

    /**
     * 批量删除表单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFormMetaByIds(String[] ids);
}
