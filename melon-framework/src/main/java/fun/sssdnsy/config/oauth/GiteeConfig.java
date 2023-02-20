package fun.sssdnsy.config.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author pengzh
 * @desc Gitee登录配置
 * @since 2023-02-18
 */
@Configuration
@PropertySource("classpath:application-oauth.yml")
public class GiteeConfig {

    @Value("${gitee.clientId}")
    private String clientId;

    @Value("${gitee.clientSecret}")
    private String clientSecret;

    @Value("${gitee.redirectURI}")
    private String redirectURI;

    @Value("${gitee.authorizeURL}")
    private String authorizeURL;

    @Value("${gitee.accessToken}")
    private String accessToken;

    @Value("${gitee.userInfo}")
    private String userInfo;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getAuthorizeURL() {
        return authorizeURL;
    }

    public void setAuthorizeURL(String authorizeURL) {
        this.authorizeURL = authorizeURL;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
