package fun.sssdnsy.service.impl;

import fun.sssdnsy.domain.FormMeta;
import fun.sssdnsy.mapper.FormMetaMapper;
import fun.sssdnsy.service.IFormMetaService;
import fun.sssdnsy.utils.StringUtils;
import fun.sssdnsy.utils.uuid.IdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 表单Service业务层处理
 *
 * @author melon
 * @date 2023-07-06
 */
@Service
public class FormMetaServiceImpl implements IFormMetaService {
    @Resource
    private FormMetaMapper formMetaMapper;

    /**
     * 查询表单
     *
     * @param id 表单主键
     * @return 表单
     */
    @Override
    public FormMeta selectFormMetaById(String id) {
        return formMetaMapper.selectFormMetaById(id);
    }

    /**
     * 查询表单列表
     *
     * @param formMeta 表单
     * @return 表单
     */
    @Override
    public List<FormMeta> selectFormMetaList(FormMeta formMeta) {
        return formMetaMapper.selectFormMetaList(formMeta);
    }

    /**
     * 新增表单
     *
     * @param formMeta 表单
     * @return 结果
     */
    @Override
    public int save(FormMeta formMeta) {
        int num;
        if (StringUtils.isNotBlank(formMeta.getId())) {
            FormMeta formMetaUpd = formMetaMapper.selectFormMetaById(formMeta.getId());
            formMetaUpd.setVersion(formMetaUpd.getVersion() + 1);
            formMetaUpd.setFormJson(formMeta.getFormJson());
            num = formMetaMapper.updateFormMeta(formMetaUpd);
        } else {
            formMeta.setId(IdUtils.getSuid());
            num = formMetaMapper.insertFormMeta(formMeta);
        }
        return num;
    }


    /**
     * 批量删除表单
     *
     * @param ids 需要删除的表单主键
     * @return 结果
     */
    @Override
    public int deleteFormMetaByIds(String[] ids) {
        return formMetaMapper.deleteFormMetaByIds(ids);
    }

    /**
     * 删除表单信息
     *
     * @param id 表单主键
     * @return 结果
     */
    @Override
    public int deleteFormMetaById(String id) {
        return formMetaMapper.deleteFormMetaById(id);
    }
}
