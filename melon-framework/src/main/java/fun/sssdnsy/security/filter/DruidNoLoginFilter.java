package fun.sssdnsy.security.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.alibaba.druid.support.http.ResourceServlet.SESSION_USER_KEY;

/**
 * @Desc druid免登录过滤器
 * @Author pengzh
 * @Since 2023-07-18
 */
@Component
@WebFilter(urlPatterns = "/druid/*")
public class DruidNoLoginFilter implements Filter {

    @Value("${spring.datasource.druid.statViewServlet.login-username}")
    private String loginUserName;

    @Value("${spring.datasource.druid.statViewServlet.no-login}")
    private boolean noLogin;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest  httpServletRequest  = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession         session             = httpServletRequest.getSession(false);
        if (noLogin && session != null && session.getAttribute(SESSION_USER_KEY) == null) {
            session.setAttribute(SESSION_USER_KEY, loginUserName);
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

}
