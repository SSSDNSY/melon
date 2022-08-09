package fun.sssdnsy.admin.service;

import fun.sssdnsy.admin.core.model.User;
import fun.sssdnsy.admin.core.util.CookieUtil;
import fun.sssdnsy.admin.core.util.JacksonUtil;
import fun.sssdnsy.admin.core.util.ReturnT;
import fun.sssdnsy.admin.dao.UserDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * Login Service
 *
 * @author xuxueli 2018-02-04 03:25:55
 */
@Configuration
public class LoginService {

    public static final String LOGIN_IDENTITY = "XXL_CONF_LOGIN_IDENTITY";

    @Resource
    private UserDao userDao;

    private String makeToken(User user){
        String tokenJson = JacksonUtil.writeValueAsString(user);
        String tokenHex = new BigInteger(tokenJson.getBytes()).toString(16);
        return tokenHex;
    }
    private User parseToken(String tokenHex){
        User user = null;
        if (tokenHex != null) {
            String tokenJson = new String(new BigInteger(tokenHex, 16).toByteArray());      // username_password(md5)
            user = JacksonUtil.readValue(tokenJson, User.class);
        }
        return user;
    }

    /**
     * login
     *
     * @param response
     * @param usernameParam
     * @param passwordParam
     * @param ifRemember
     * @return
     */
    public ReturnT<String> login(HttpServletResponse response, String usernameParam, String passwordParam, boolean ifRemember){

        User user = userDao.load(usernameParam);
        if (user == null) {
            return new ReturnT<String>(500, "账号或密码错误");
        }

        String passwordParamMd5 = DigestUtils.md5DigestAsHex(passwordParam.getBytes());
        if (!user.getPassword().equals(passwordParamMd5)) {
            return new ReturnT<String>(500, "账号或密码错误");
        }

        String loginToken = makeToken(user);

        // do login
        CookieUtil.set(response, LOGIN_IDENTITY, loginToken, ifRemember);
        return ReturnT.SUCCESS;
    }

    /**
     * logout
     *
     * @param request
     * @param response
     */
    public void logout(HttpServletRequest request, HttpServletResponse response){
        CookieUtil.remove(request, response, LOGIN_IDENTITY);
    }

    /**
     * logout
     *
     * @param request
     * @return
     */
    public User ifLogin(HttpServletRequest request){
        String cookieToken = CookieUtil.getValue(request, LOGIN_IDENTITY);
        if (cookieToken != null) {
            User cookieUser = parseToken(cookieToken);
            if (cookieUser != null) {
                User dbUser = userDao.load(cookieUser.getUsername());
                if (dbUser != null) {
                    if (cookieUser.getPassword().equals(dbUser.getPassword())) {
                        return dbUser;
                    }
                }
            }
        }
        return null;
    }

}
