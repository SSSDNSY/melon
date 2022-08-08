package fun.sssdnsy.melon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.Application
 * @desc Application
 * @since 2022-07-24
 */
@SpringBootApplication
@MapperScan("fun.sssdnsy.melon.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
