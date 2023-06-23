package fun.sssdnsy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 变量信息
 */
@Getter
@Setter
@ToString
public class VariableInfo {

    String variableName;

    String variableTypeName;

    String value;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date lastUpdatedTime;

}
