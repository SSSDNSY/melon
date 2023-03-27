package fun.sssdnsy.domain;

import fun.sssdnsy.core.domain.BaseEntity;
import lombok.*;

import java.util.List;

/**
 * 配置节点
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XxlConfNode extends BaseEntity {

    /**
     * 环境
     */
    private String env;
    /**
     * 配置Key
     */
    private String key;
    /**
     * 所属项目AppName
     */
    private String appname;
    /**
     * 配置描述
     */
    private String title;
    /**
     * 配置Value
     */
    private String value;

    /**
     * 配置变更Log
     */
    private List<XxlConfNodeLog> logList;

}
