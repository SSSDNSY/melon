package fun.sssdnsy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import fun.sssdnsy.annotation.Excel;
import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 执行实例对象 act_ru_execution
 *
 * @date 2022-07-13
 */
@Getter
@Setter
@ToString
public class ActRuExecution extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     *
     */
    @Excel(name = "")
    private Long rev;

    /**
     *
     */
    @Excel(name = "")
    private String procInstId;

    /**
     *
     */
    @Excel(name = "")
    private String businessKey;

    /**
     *
     */
    @Excel(name = "")
    private String parentId;

    /**
     *
     */
    @Excel(name = "")
    private String procDefId;

    /**
     *
     */
    @Excel(name = "")
    private String superExec;

    /**
     *
     */
    @Excel(name = "")
    private String rootProcInstId;

    /**
     *
     */
    @Excel(name = "")
    private String actId;

    /**
     *
     */
    @Excel(name = "")
    private Integer isActive;

    /**
     *
     */
    @Excel(name = "")
    private Integer isConcurrent;

    /**
     *
     */
    @Excel(name = "")
    private Integer isScope;

    /**
     *
     */
    @Excel(name = "")
    private Integer isEventScope;

    /**
     *
     */
    @Excel(name = "")
    private Integer isMiRoot;

    /**
     *
     */
    @Excel(name = "")
    private Long suspensionState;

    /**
     *
     */
    @Excel(name = "")
    private Long cachedEntState;

    /**
     *
     */
    @Excel(name = "")
    private String tenantId;

    /**
     *
     */
    @Excel(name = "")
    private String name;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     *
     */
    @Excel(name = "")
    private String startUserId;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lockTime;

    /**
     *
     */
    @Excel(name = "")
    private Integer isCountEnabled;

    /**
     *
     */
    @Excel(name = "")
    private Long evtSubscrCount;

    /**
     *
     */
    @Excel(name = "")
    private Long taskCount;

    /**
     *
     */
    @Excel(name = "")
    private Long jobCount;

    /**
     *
     */
    @Excel(name = "")
    private Long timerJobCount;

    /**
     *
     */
    @Excel(name = "")
    private Long suspJobCount;

    /**
     *
     */
    @Excel(name = "")
    private Long deadletterJobCount;

    /**
     *
     */
    @Excel(name = "")
    private Long varCount;

    /**
     *
     */
    @Excel(name = "")
    private Long idLinkCount;

}
