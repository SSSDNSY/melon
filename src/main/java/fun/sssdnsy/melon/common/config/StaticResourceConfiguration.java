package fun.sssdnsy.melon.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author pengzh
 * @desc
 * @class StaticResourceHandler
 * @since 2022-08-08
 */
@Configuration
@EnableWebMvc
public class StaticResourceConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler("/static/**") // « /static/css/myStatic.css
//                .addResourceLocations("classpath:/static/") // Default Static Loaction
//                .setCachePeriod(3600)
//                .resourceChain(true) // 4.1
//                .addResolver(new WebJarsResourceResolver())
//                .addResolver(new PathResourceResolver()); //4.1
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("/META-INF/resources/webjars/")
//                .resourceChain(false)
//                .addResolver(new WebJarsResourceResolver())
//                .addResolver(new PathResourceResolver());
//        registry.setOrder(1);
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/resources/static/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}