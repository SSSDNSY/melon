package fun.sssdnsy.web.service;

import fun.sssdnsy.constant.CacheConstants;
import fun.sssdnsy.constant.Constants;
import fun.sssdnsy.core.domain.entity.SysUser;
import fun.sssdnsy.exception.user.UserPasswordNotMatchException;
import fun.sssdnsy.exception.user.UserPasswordRetryLimitExceedException;
import fun.sssdnsy.manager.AsyncManager;
import fun.sssdnsy.manager.factory.AsyncFactory;
import fun.sssdnsy.security.context.AuthenticationContextHolder;
import fun.sssdnsy.utils.MessageUtils;
import fun.sssdnsy.utils.SecurityUtils;
import fun.sssdnsy.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 登录密码方法
 *
 * @author sssdnsy
 */
@Component
public class SysPasswordService {

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username) {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    public void validate(SysUser user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String         username                            = usernamePasswordAuthenticationToken.getName();
        String         password                            = usernamePasswordAuthenticationToken.getCredentials().toString();

        Integer retryCount = RedisUtils.getCacheObject(getCacheKey(username));
        if (retryCount == null) {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount).intValue()) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount, lockTime)));
            throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
        }

        if (!matches(user, password)) {
            retryCount = retryCount + 1;
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.retry.limit.count", retryCount)));
            RedisUtils.setCacheObject(getCacheKey(user.getNickName()), retryCount, Duration.of(lockTime, ChronoUnit.MINUTES));
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(SysUser user, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName) {
        if (RedisUtils.hasKey(getCacheKey(loginName))) {
            RedisUtils.deleteObject(getCacheKey(loginName));
        }
    }
}
