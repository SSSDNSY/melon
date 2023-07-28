package fun.sssdnsy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.sssdnsy.domain.ActBusinessRule;
import fun.sssdnsy.domain.ActBusinessRuleParam;
import fun.sssdnsy.domain.bo.ActBusinessRuleBo;
import fun.sssdnsy.domain.vo.ActBusinessRuleVo;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.utils.JsonUtils;
import fun.sssdnsy.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import fun.sssdnsy.mapper.ActBusinessRuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fun.sssdnsy.service.IActBusinessRuleService;

import java.util.Collection;
import java.util.List;

/**
 * 业务规则Service业务层处理
 *
 * @author gssong
 * @date 2021-12-16
 */
@RequiredArgsConstructor
@Service
public class ActBusinessRuleServiceImpl implements IActBusinessRuleService {

    private final ActBusinessRuleMapper baseMapper;

    @Override
    public ActBusinessRuleVo queryById(Long id) {
        ActBusinessRuleVo vo = baseMapper.selectVoById(id);
        if(vo==null){
            return vo;
        }
        if (StringUtils.isNotBlank(vo.getParam())) {
            List<ActBusinessRuleParam> params = JsonUtils.parseArray(vo.getParam(), ActBusinessRuleParam.class);
            vo.setBusinessRuleParams(params);
        }
        return vo;
    }

    @Override
    public TableDataInfo queryPageList(ActBusinessRuleBo bo, PageQuery pageQuery) {
        Page<ActBusinessRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), buildQueryWrapper(bo));
        return TableDataInfo.build(result);
    }

    @Override
    public List<ActBusinessRuleVo> queryList(ActBusinessRuleBo bo) {
        return baseMapper.selectVoList(buildQueryWrapper(bo));
    }

    private LambdaQueryWrapper<ActBusinessRule> buildQueryWrapper(ActBusinessRuleBo bo) {
        LambdaQueryWrapper<ActBusinessRule> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBeanName()), ActBusinessRule::getBeanName, bo.getBeanName());
        lqw.like(StringUtils.isNotBlank(bo.getMethod()), ActBusinessRule::getMethod, bo.getMethod());
        return lqw;
    }

    @Override
    public Boolean insertByBo(ActBusinessRuleBo bo) {
        List<ActBusinessRuleParam> businessRuleParams = bo.getBusinessRuleParams();
        ActBusinessRule            add                = BeanUtil.toBean(bo, ActBusinessRule.class);
        if (CollectionUtil.isNotEmpty(businessRuleParams)) {
            add.setParam(JsonUtils.toJsonString(businessRuleParams));
        }
        int flag = baseMapper.insert(add);
        if (flag > 0) {
            bo.setId(add.getId());
        }
        return flag > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(ActBusinessRuleBo bo) {
        List<ActBusinessRuleParam> businessRuleParams = bo.getBusinessRuleParams();
        ActBusinessRule            update             = BeanUtil.toBean(bo, ActBusinessRule.class);
        if (CollectionUtil.isNotEmpty(businessRuleParams)) {
            update.setParam(JsonUtils.toJsonString(businessRuleParams));
        }
        return baseMapper.updateById(update) > 0;
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
