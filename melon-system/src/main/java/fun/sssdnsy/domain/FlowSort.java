package fun.sssdnsy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * 流程分类
 */
@Getter
@Setter
@ToString
public class FlowSort {

    /**
     * 主键
     */
    private Long           id;
    /**
     * 流程分类名称
     */
    private String         name;
    /**
     * 父节点ID
     */
    private Long           parentId;
    /**
     * 子类集合
     */
    private List<FlowSort> children;

}
