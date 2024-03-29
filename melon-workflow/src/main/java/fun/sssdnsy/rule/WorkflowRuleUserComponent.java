package fun.sssdnsy.rule;

import cn.hutool.core.util.ObjectUtil;
import fun.sssdnsy.core.domain.entity.SysUser;
import fun.sssdnsy.exception.user.UserException;
import fun.sssdnsy.mapper.SysUserMapper;
import fun.sssdnsy.utils.spring.SpringUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;

/**
 * 业务规则选人 返回人员id，多个id用英文逗号隔开
 *
 * @author gssong
 */
@Component
public class WorkflowRuleUserComponent {

    /**
     * 按用户id查询
     *
     * @param userId
     * @return
     */
    public Long queryUserById(Long userId) {
        SysUserMapper userMapper = SpringUtils.getBean(SysUserMapper.class);
        SysUser       sysUser    = userMapper.selectUserById(userId);
        if (ObjectUtil.isNull(sysUser)) {
            throw new UserException("未找到审批人员");
        }
        return sysUser.getUserId();
    }

    /**
     * @Description: 获取流程发起人
     * @param: processInstanceId
     * @return: java.lang.String
     * @author: gssong
     * @Date: 2022/8/10 22:41
     */
    public String startUserId(String processInstanceId) {
        RuntimeService  runtimeService  = SpringUtils.getBean(RuntimeService.class);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        return processInstance.getStartUserId();
    }


}
