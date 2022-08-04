package fun.sssdnsy.melon.api;

import fun.sssdnsy.melon.dao.entity.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * @author pengzh
 * @desc
 * @class ApiControllerTest
 * @since 2022-07-26
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before("")
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

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

    @Test
    public void getUpagePages() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/page")
                        .content("{\n" +
                                "  \"page\": \"1\",\n" +
                                "  \"size\": \"1\",\n" +
                                "  \"sort\": \"id\"\n" +
                                "}".getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON) //数据的格式
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn()
                .getResponse()
                .getContentAsString(); //将相应的数据转换为字符

        System.out.println(responseString);
    }


}
