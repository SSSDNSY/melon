package fun.sssdnsy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 任务详情
 */
@Getter
@Setter
@ToString
public class TaskInfo {

    String taskId;

    String processInstanceId;

    String executionId;

    String businessKey;

    String processName;

    String taskName;

    String starter;

    String assignee;

    String startTime;

    String endTime;

    String createTime;

    String formKey;

    String comment;

    Integer pageSize;

    Integer pageNum;

}
