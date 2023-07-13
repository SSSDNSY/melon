package fun.sssdnsy.domain;

import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-07-13
 */
@Getter
@Setter
@ToString
public class FlowElementAttr extends BaseEntity {

    /**
     * 主键
     */
    private String id;

    /**
     * 流程标识建
     */
    private String processKey;

    /**
     * 节点标识
     */
    private String taskDefKey;

    /**
     * 驳回规则
     */
    private String rejectRule;

    /**
     * 驳回方式
     */
    private String rejectWay;

    /**
     * 跳转规则
     */
    private String jumpRule;

    /**
     * 抄送规则
     */
    private String copytoRule;

    /**
     * 自动抄送规则
     */
    private String autoCopytoRule;

    /**
     * 可移交
     */
    private int transfer;

    /**
     * 可加签
     */
    private int addsign;

    /**
     * 可结束
     */
    private int endable;

    /**
     * 可拒绝
     */
    private int refuable;

    /**
     * 表单编码
     */
    private String formId;

    /**
     * 表单url
     */
    private String formUrl;

    /**
     * 表单json
     */
    private String formJson;

    /**
     * 版本号
     */
    private Integer version;


}
