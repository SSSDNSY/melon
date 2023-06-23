package fun.sssdnsy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 流程实例信息
 */
@Getter
@Setter
@ToString
public class FlowInfo {
    String processInstanceId;

    String executionId;

    String parentExecutionId;

    String businessKey;

    String name;

    Boolean suspended;

    Boolean ended;
    // 是否存活
    Boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date endTime;

    String startUserId;
    // 当前节点
    String currentTask;
    // 当前办理人
    String assignee;

}
