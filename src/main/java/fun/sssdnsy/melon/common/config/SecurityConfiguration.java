package fun.sssdnsy.melon.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.common.config.MySecurityConfig
 * @desc
 * @since 2022-07-25
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        return http.build();
    }

}
