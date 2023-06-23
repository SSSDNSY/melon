package fun.sssdnsy.web.service;

import com.alibaba.fastjson2.JSONObject;
import fun.sssdnsy.constant.Constants;
import fun.sssdnsy.core.domain.entity.SysUser;
import fun.sssdnsy.core.domain.model.LoginUser;
import fun.sssdnsy.exception.ServiceException;
import fun.sssdnsy.exception.user.UserPasswordNotMatchException;
import fun.sssdnsy.manager.AsyncManager;
import fun.sssdnsy.manager.factory.AsyncFactory;
import fun.sssdnsy.security.context.AuthenticationContextHolder;
import fun.sssdnsy.service.ISysUserService;
import fun.sssdnsy.utils.DateUtils;
import fun.sssdnsy.utils.MessageUtils;
import fun.sssdnsy.utils.SecurityUtils;
import fun.sssdnsy.utils.ServletUtils;
import fun.sssdnsy.utils.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author sssdnsy
 */
@Component
public class SysOauthService {

    public static final String INIT_PASSWORD = "admin123";
    private static final Logger log = LoggerFactory.getLogger(SysOauthService.class);
    @Resource
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private ISysUserService userService;


    /**
     * 登录验证
     *
     * @return 结果
     */
    public String checkIn(JSONObject userInfo) {

        SysUser sysUser = parseSysUserFromUserInfo(userInfo);
        SysUser dbUser = userService.selectUserById(sysUser.getUserId());

        Authentication authentication = null;
        LoginUser loginUser = null;
        String username = null;
        String password = INIT_PASSWORD;
        if (dbUser != null) {  //已注册
            username = dbUser.getUserName();

        } else { //未注册
            genPassword4SocialUser(sysUser);
            username = sysUser.getUserName();
            userService.insertUser(sysUser);
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
        }
        authentication = getAuthentication(username, password);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());

        // 生成token
        return tokenService.createToken(loginUser);
    }

    public Authentication getAuthentication(String username, String password) {
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        return authentication;
    }

    private SysUser parseSysUserFromUserInfo(JSONObject userInfo) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userInfo.getLong("id"));
        sysUser.setNickName(userInfo.getString("name"));
        sysUser.setUserName(userInfo.getString("name"));
        sysUser.setAvatar(userInfo.getString("avatar_url"));
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        sysUser.setRoleIds(new Long[]{2l});
        sysUser.setPostIds(new Long[]{4l});
        sysUser.setCreateBy("sys");
        sysUser.setCreateTime(userInfo.getDate("created_at"));
        sysUser.setUpdateBy("sys");
        sysUser.setUpdateTime(userInfo.getDate("updated_at"));
        sysUser.setRemark("Gitee User");
        return sysUser;
    }

    private void genPassword4SocialUser(SysUser sysUser) {
        //String password =RandomStringUtils.randomAscii(16);
        String password = INIT_PASSWORD;
        String encryptPassword = SecurityUtils.encryptPassword(password);
        sysUser.setPassword(encryptPassword);
        log.info(">>> create user:[{}],password:[{}],password:[{}]", sysUser.getUserName(), password, encryptPassword);
    }


    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }
}
