package fun.sssdnsy.web.controller.common;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import fun.sssdnsy.annotation.Anonymous;
import fun.sssdnsy.config.oauth.GiteeConfig;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.utils.ServletUtils;
import fun.sssdnsy.utils.uuid.IdUtils;
import fun.sssdnsy.web.service.SysOauthService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pengzh
 * @desc
 * @since 2023-02-19
 */
@Controller
@RequestMapping("/oauth")
public class OauthController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(OauthController.class);

    @Resource
    private GiteeConfig giteeConfig;

    @Value("${token.expireTime}")
    private Integer tokenExpireTime;

    @Resource
    private SysOauthService oauthService;

    /**
     * 获得跳转到Gitee登录页的url,前台直接a连接访问
     *
     * @return
     */

    @ResponseBody
    @GetMapping(value = "/gitee/getGiteeCode")
    public AjaxResult getGiteeCode() {

        String clientId = giteeConfig.getClientId();
        // 授权地址 ,进行Encode转码
        String authorizeURL = giteeConfig.getAuthorizeURL();

        // 回调地址 ,回调地址要进行Encode转码
        String redirectUri = giteeConfig.getRedirectURI();

        // 用于第三方应用防止CSRF攻击
        String uuid = IdUtils.getSuid();

        // 拼接url
        StringBuilder url = new StringBuilder();
        url.append(authorizeURL);
        url.append("?client_id=" + clientId);
        url.append("&response_type=code");
        // 转码
        url.append("&redirect_uri=" + redirectUri);
        url.append("&state=" + uuid);
        url.append("&scope=user_info");

        return success(url.toString());
    }

    @Anonymous
    @GetMapping("/callback/gitee")
    public String giteeCallback(@RequestParam("code") String code) throws Exception {
        String token = "";
        // TODO
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(giteeConfig.getAccessToken());
        JSONObject jsonObj = JSONObject.of();
        jsonObj.put("grant_type", "authorization_code");
        jsonObj.put("code", code);
        jsonObj.put("redirect_uri", giteeConfig.getRedirectURI());
        jsonObj.put("client_id", giteeConfig.getClientId());
        jsonObj.put("client_secret", giteeConfig.getClientSecret());
        jsonObj.put("response_type", "code");
        String jsonStr = jsonObj.toJSONString();
        HttpEntity httpEntity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
        httpPost.setEntity(httpEntity);
        HttpResponse response = httpClient.execute(httpPost);

        //2、处理
        //获取到accessToken

        String json = EntityUtils.toString(response.getEntity());
        log.info("获取到的tokenJson为：" + json);
        JSONObject jsonObject = JSON.parseObject(json);
        String accessToken = jsonObject.getString("access_token");

        //gitee还需要再去请求user去获取数据
        String userUrl = giteeConfig.getUserInfo() + "?access_token=" + accessToken;
        HttpClient httpClientUser = HttpClientBuilder.create().build();
        HttpGet httpPostUser = new HttpGet(userUrl); //记得用httpGet请求，否则会405拒绝请求
        HttpResponse responseUser = httpClientUser.execute(httpPostUser);

        String user = EntityUtils.toString(responseUser.getEntity());
        log.info("gitee用户信息", user);

        JSONObject jsonObjectUser = JSON.parseObject(user);

        //登录或者注册这个社交用户
        token = oauthService.checkIn(jsonObjectUser);
        setTokenCookie(token);

        return redirect("http://pengzh.fun:80/");
    }


    private void setTokenCookie(String token) {
        HttpServletResponse httpServletResponse = ServletUtils.getResponse();
        Cookie cookie = new Cookie("oauth2Token", token);
        cookie.setPath("/");
        cookie.setMaxAge(tokenExpireTime * 60);
        cookie.setHttpOnly(false);
        httpServletResponse.addCookie(cookie);
    }


}
