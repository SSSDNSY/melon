package fun.sssdnsy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class DeadLetterJob {

    String id;

    Date dueDate;

    String exceptionMessage;

    String jobHandlerType;

    String jobType;

    String processDefId;

    String processInstanceId;

    String executionId;

}
