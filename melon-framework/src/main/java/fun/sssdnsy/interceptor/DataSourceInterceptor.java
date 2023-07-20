package fun.sssdnsy.interceptor;

import fun.sssdnsy.datasource.DynamicDataSourceContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截请求切换数据源
 */
@Component
public class DataSourceInterceptor implements HandlerInterceptor {

    /**
     * 前置拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String dataSourceType = request.getParameter("dataSourceType");
        if (StringUtils.isNotEmpty(dataSourceType)) {
            // 切换数据源
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType);
        }
        return true;
    }

    /**
     * 后置拦截
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    
}
