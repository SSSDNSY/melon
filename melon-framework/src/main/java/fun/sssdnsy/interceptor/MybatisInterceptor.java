package fun.sssdnsy.interceptor;

import fun.sssdnsy.core.domain.BaseEntity;
import fun.sssdnsy.core.domain.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * @Desc 公告字段拦截器
 * @Author pengzh
 * @Since 2023-03-30
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = MybatisInterceptor.UPDATE, args = {MappedStatement.class, Object.class})
})
public class MybatisInterceptor implements Interceptor {

    public static final String UPDATE = "update";


    @Override
    public Object intercept(Invocation invocation) throws IllegalAccessException, InvocationTargetException {
        fillField(invocation);
        return invocation.proceed();
    }

    /**
     * 填充创建、修改公共信息
     */
    private void fillField(Invocation invocation) {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof BaseEntity) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !((authentication.getPrincipal()) instanceof  LoginUser)) {
                return;
            }
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            String username = loginUser.getUsername();
            BaseEntity baseEntity = (BaseEntity) parameter;
            if (Objects.equals(SqlCommandType.INSERT, mappedStatement.getSqlCommandType())) {
                baseEntity.setCreateBy(username);
                baseEntity.setCreateTime(new Date());
            } else if (Objects.equals(SqlCommandType.UPDATE, mappedStatement.getSqlCommandType())) {
                baseEntity.setUpdateBy(username);
                baseEntity.setUpdateTime(new Date());
            }
        }

    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}