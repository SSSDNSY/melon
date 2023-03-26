package fun.sssdnsy.domain;

import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 配置日志
 */
@Getter
@Setter
@ToString
public class XxlConfNodeLog extends BaseEntity {

    /**
     * 配置环境
     */
    private String env;
    /**
     * 配置Key
     */
    private String key;
    /**
     * 配置描述
     */
    private String title;
    /**
     * 配置Value
     */
    private String value;

}
