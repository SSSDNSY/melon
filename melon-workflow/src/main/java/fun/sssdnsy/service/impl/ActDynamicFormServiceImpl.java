package fun.sssdnsy.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.domain.ActDynamicForm;
import fun.sssdnsy.domain.bo.ActDynamicFormBo;
import fun.sssdnsy.domain.vo.ActDynamicFormVo;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.exception.ServiceException;
import fun.sssdnsy.mapper.ActProcessFormMapper;
import fun.sssdnsy.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fun.sssdnsy.service.IActDynamicFormService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 流程单Service业务层处理
 *
 * @author gssong
 * @date 2022-08-11
 */
@RequiredArgsConstructor
@Service
public class ActDynamicFormServiceImpl implements IActDynamicFormService {

    private final ActProcessFormMapper baseMapper;

    /**
     * 查询动态表单
     */
    @Override
    public ActDynamicFormVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询动态表单列表
     */
    @Override
    public TableDataInfo queryPageList(ActDynamicFormBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ActDynamicForm> lqw    = buildQueryWrapper(bo);
        Page<ActDynamicFormVo>             result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询启用动态表单列表
     */
    @Override
    public TableDataInfo queryPageEnableList(ActDynamicFormBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ActDynamicForm> lqw = buildQueryWrapper(bo);
        lqw.eq(ActDynamicForm::getStatus, true);
        Page<ActDynamicFormVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询动态表单列表
     */
    @Override
    public List<ActDynamicFormVo> queryList(ActDynamicFormBo bo) {
        LambdaQueryWrapper<ActDynamicForm> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ActDynamicForm> buildQueryWrapper(ActDynamicFormBo bo) {
        Map<String, Object>                params = bo.getParams();
        LambdaQueryWrapper<ActDynamicForm> lqw    = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getFormKey()), ActDynamicForm::getFormKey, bo.getFormKey());
        lqw.like(StringUtils.isNotBlank(bo.getFormName()), ActDynamicForm::getFormName, bo.getFormName());
        lqw.orderByAsc(ActDynamicForm::getOrderNo);
        return lqw;
    }

    /**
     * 新增动态表单
     */
    @Override
    public Boolean insertByBo(ActDynamicFormBo bo) {
        ActDynamicForm add = BeanUtil.toBean(bo, ActDynamicForm.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改动态表单
     */
    @Override
    public Boolean updateByBo(ActDynamicFormBo bo) {
        ActDynamicForm update = BeanUtil.toBean(bo, ActDynamicForm.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    @Override
    public Boolean editForm(ActDynamicFormBo bo) {
        ActDynamicForm update = BeanUtil.toBean(bo, ActDynamicForm.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ActDynamicForm entity) {
        LambdaQueryWrapper<ActDynamicForm> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ActDynamicForm::getFormKey, entity.getFormKey());
        if (entity.getId() != null) {
            wrapper.ne(ActDynamicForm::getId, entity.getId());
            List<ActDynamicForm> actDynamicForms = baseMapper.selectList(wrapper);
            validException(actDynamicForms);
        } else {
            List<ActDynamicForm> actDynamicForms = baseMapper.selectList(wrapper);
            validException(actDynamicForms);
        }
    }

    private void validException(List<ActDynamicForm> actDynamicForms) {

        if (CollectionUtil.isNotEmpty(actDynamicForms)) {
            throw new ServiceException("表单key已存在");
        }

        if (CollectionUtil.isNotEmpty(actDynamicForms) && actDynamicForms.size() > 1) {
            throw new ServiceException(actDynamicForms.get(0).getFormKey() + "表单key存在" + actDynamicForms.size() + "个,请检查数据！");
        }
    }

    /**
     * 批量删除动态表单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
