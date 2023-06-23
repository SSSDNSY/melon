package fun.sssdnsy.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 模型变量
 */
@Getter
@Setter
@ToString
public class ModelParam {

    protected String name;

    protected String key;

    protected String category;

    protected Integer version;

    protected String description;

}
