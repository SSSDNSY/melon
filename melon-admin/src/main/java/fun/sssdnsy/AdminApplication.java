package fun.sssdnsy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author sssdnsy
 */
@EnableScheduling
@SpringBootApplication(exclude = {
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
@ComponentScan({"fun.sssdnsy", "fun.sssdnsy.web.controller"})
public class AdminApplication {
    public static void main(String[] args) {
        System.setProperty("pagehelper.banner", "false");
        SpringApplication application = new SpringApplication(AdminApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
    }
}
