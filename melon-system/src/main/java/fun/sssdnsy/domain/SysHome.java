package fun.sssdnsy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 首页信息
 */
@Getter
@Setter
@ToString
public class SysHome {
    /**
     * 用户数
     */
    private Integer userNumber;

    /**
     * 消息数
     */
    private Integer messageNumber;

    /**
     * 流程数
     */
    private Integer flowNumber;

    /**
     * 任务数
     */
    private Integer taskNumber;

}
