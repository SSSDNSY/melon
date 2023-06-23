package fun.sssdnsy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 流程实例
 */
@Getter
@Setter
@ToString
public class Process {
    String id;

    String deploymentId;

    String name;

    String resourceName;

    String key;

    String diagramresourceName;

    Integer version;

    Boolean suspended;


}
