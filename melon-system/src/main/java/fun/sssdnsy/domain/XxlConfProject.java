package fun.sssdnsy.domain;

import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 配置项目
 */
@Getter
@Setter
@ToString
public class XxlConfProject extends BaseEntity {

    /**
     * 项目AppName
     */
    private String appname;
    /**
     * 项目名称
     */
    private String title;

}
