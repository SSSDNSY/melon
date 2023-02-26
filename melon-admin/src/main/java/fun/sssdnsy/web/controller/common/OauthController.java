package fun.sssdnsy.web.controller.common;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import fun.sssdnsy.config.oauth.GiteeConfig;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.utils.uuid.IdUtils;
import nonapi.io.github.classgraph.utils.URLPathEncoder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author pengzh
 * @desc
 * @since 2023-02-19
 */
@Controller
@RequestMapping("/oauth")
public class OauthController extends BaseController {

    @Resource
    private GiteeConfig giteeConfig;

    /**
     * 获得跳转到Gitee登录页的url,前台直接a连接访问
     *
     * @return
     */
    @GetMapping("/gitee/getGiteeCode")
    public String getGiteeCode() {
        // 授权地址 ,进行Encode转码
        String authorizeURL = giteeConfig.getAuthorizeURL();

        // 回调地址 ,回调地址要进行Encode转码
        String redirectUri = giteeConfig.getRedirectURI();

        // 用于第三方应用防止CSRF攻击
        String uuid = IdUtils.randomUUID();

        // 拼接url
        StringBuilder url = new StringBuilder();
        url.append(authorizeURL);
        url.append("?client_id=" + giteeConfig.getClientId());
        url.append("&response_type=code");
        // 转码
        url.append("&redirect_uri=" + URLPathEncoder.encodePath(redirectUri));
        url.append("&state=" + uuid);
        url.append("&scope=user_info");

        return redirect(url.toString());
    }

    @GetMapping("/callback/gitee")
    public String gitee(@RequestParam("code") String code) throws Exception {

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
        if (response.getStatusLine().getStatusCode() == 200) {
            //获取到accessToken

            String json = EntityUtils.toString(response.getEntity());
            System.out.println("获取到的token为：" + json);
            JSONObject jsonObject = JSON.parseObject(json);
            String accessToken = jsonObject.getString("access_token");
            System.out.println("获取到的access_token：" + accessToken);

            //gitee还需要再去请求user去获取数据
            //  GiteeUser giteeUser =  giteeComponent.getGiteeUser(json);
            String urluser = giteeConfig.getUserInfo() + "?access_token=" + accessToken;
            HttpClient httpClientUser = HttpClientBuilder.create().build();
            HttpGet httpPostUser = new HttpGet(urluser);           //记得用httpGet请求，否则会405拒绝请求
            HttpResponse responseUser = httpClientUser.execute(httpPostUser);

            String user = EntityUtils.toString(responseUser.getEntity());
            System.out.println("gitee用户信息" + user);

            JSONObject jsonObjectUser = JSON.parseObject(user);
            String id = jsonObjectUser.getString("id");
            System.out.println(id);
            String name = jsonObjectUser.getString("name");
            System.out.println(name);
            String bio = jsonObjectUser.getString("bio");
            System.out.println(bio);

            //知道当前是哪个社交用户登录成功
            //1、当前用户如果是第一次进网站，就自动注册进来（为当前社交用户生成一个会员信息账号,以后这个社交账号就对应指定的会员）
            //登录或者注册这个社交用户
//            R r = memberFeignService.oauth2Login(giteeUser);
            if (true) {
//                MemberRespVo memberRespVo = r.getData(new TypeReference<MemberRespVo>() {
//                });
                logger.info("登录成功，用户信息：" + jsonObjectUser);
                //TODO 1、默认发的令牌 session=dadas,作用域只是当前域，（解决子域与父域session共享问题）
                //TODO 2、使用json的序列化方式来序列化对象数据到redis中
                //session.setAttribute(AuthServerConstant.SESSION_LOGIN_KEY, memberRespVo);
                //2、登录成功就跳回首页
                return "redirect:http://127.0.0.1:8081/";
            } else {
                return "redirect:http://127.0.0.1:8081/login.html";
            }
        } else {
            return "redirect:http://127.0.0.1:8081/login.html";
        }

    }


}
