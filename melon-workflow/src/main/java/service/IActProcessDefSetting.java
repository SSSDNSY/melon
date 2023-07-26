package service;


import domain.ActProcessDefSetting;
import domain.bo.ActProcessDefSettingBo;
import domain.vo.ActProcessDefSettingVo;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.domain.R;
import fun.sssdnsy.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 流程定义设置Service接口
 *
 * @author gssong
 * @date 2022-08-28
 */
public interface IActProcessDefSetting {

    /**
     * 查询流程定义设置
     */
    ActProcessDefSettingVo queryById(Long id);

    /**
     * 按流程定义id查询流程定义设置详细
     */
    ActProcessDefSettingVo getProcessDefSettingByDefId(String defId);

    /**
     * 按流程定义id集合查询流程定义设置详细集合
     */
    List<ActProcessDefSettingVo> getProcessDefSettingByDefIds(List<String> defIds);

    /**
     * 校验表单是否关联
     */
    AjaxResult checkProcessDefSetting(String defId, String param, Integer businessType);

    /**
     * 查询流程定义设置列表
     */
    TableDataInfo queryPageList(ActProcessDefSettingBo bo, PageQuery pageQuery);

    /**
     * 查询流程定义设置列表
     */
    List<ActProcessDefSettingVo> queryList(ActProcessDefSettingBo bo);

    /**
     * 修改流程定义设置
     */
    Boolean insertByBo(ActProcessDefSettingBo bo);

    /**
     * 修改流程定义设置
     */
    Boolean updateByBo(ActProcessDefSettingBo bo);

    /**
     * 校验并批量删除流程定义设置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 按照formId查询
     */
    ActProcessDefSetting queryByFormId(Long formId);
}
