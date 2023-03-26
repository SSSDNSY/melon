package fun.sssdnsy.domain;

import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xuxueli 2015-9-4 15:26:01
 */
@Getter
@Setter
@ToString
public class XxlConfNodeMsg extends BaseEntity {
    /**
     * id
     */
    private int id;
    /**
     * 环境
     */
    private String env;
    /**
     * 键
     */
    private String key;
    /**
     * 值
     */
    private String value;


}
