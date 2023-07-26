package service;


import domain.bo.ActBusinessFormBo;
import domain.vo.ActBusinessFormVo;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 业务表单Service接口
 *
 * @author gssong
 * @date 2022-08-19
 */
public interface IActBusinessFormService {

    /**
     * 查询业务表单
     */
    ActBusinessFormVo queryById(Long id);

    /**
     * 查询业务表单列表
     */
    TableDataInfo queryPageList(ActBusinessFormBo bo, PageQuery pageQuery);

    /**
     * 查询业务表单列表
     */
    List<ActBusinessFormVo> queryList(ActBusinessFormBo bo);

    /**
     * 修改业务表单
     */
    ActBusinessFormVo insertByBo(ActBusinessFormBo bo);

    /**
     * 修改业务表单
     */
    ActBusinessFormVo updateByBo(ActBusinessFormBo bo);

    /**
     * 校验并批量删除业务表单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
