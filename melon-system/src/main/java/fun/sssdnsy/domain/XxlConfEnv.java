package fun.sssdnsy.domain;

import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 配置环境VO
 */
@Getter
@Setter
@ToString
public class XxlConfEnv extends BaseEntity {

    /**
     * 环境
     */
    private String env;
    /**
     * 环境名称
     */
    private String title;
    /**
     * 环境排序值
     */
    private int order;

}
