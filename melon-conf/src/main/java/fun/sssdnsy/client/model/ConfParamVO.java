package fun.sssdnsy.client.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
@Getter
@Setter
@ToString
public class ConfParamVO {

    private String accessToken;
    private String env;
    private List<String> keys;

}
