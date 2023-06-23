package fun.sssdnsy.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import fun.sssdnsy.annotation.Excel;
import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 请假对象 leaveapply
 *
 * @author shenzhanwang
 * @date 2022-04-02
 */
@Getter
@Setter
@ToString
public class Leaveapply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 请假人
     */
    @Excel(name = "请假人")
    private String userId;

    /**
     * 起始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "起始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String leaveType;

    /**
     * 原因
     */
    @Excel(name = "原因")
    private String reason;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    /**
     * 实际起始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "实际起始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date realityStartTime;

    /**
     * 实际结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "实际结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date realityEndTime;
    /**
     * 部门领导审批人
     **/
    private String deptleader;

}
