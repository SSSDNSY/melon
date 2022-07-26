package fun.sssdnsy.melon;

import fun.sssdnsy.melon.dao.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author pengzh
 * @desc
 * @class ApiControllerTest
 * @since 2022-07-26
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testGetEmployeeListSuccessWithHeaders() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/user/list";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("test", "CN");

        HttpEntity<User> requestEntity = new HttpEntity<>(null, headers);

        restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
    }

}
