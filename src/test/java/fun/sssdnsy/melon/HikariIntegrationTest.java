package fun.sssdnsy.melon;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.HikariIntegrationTest
 * @desc
 * @since 2022-07-24
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HikariIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void hikariConnectionPoolIsConfigured() {
        String name = dataSource.getClass().getName();
        log.info(name);
        assertEquals("com.zaxxer.hikari.HikariDataSource", name);
    }
}
