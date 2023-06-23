package fun.sssdnsy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import fun.sssdnsy.annotation.Excel;
import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 会议对象 meeting
 *
 * @author shenzhanwang
 * @date 2022-05-30
 */
@Getter
@Setter
@ToString
public class Meeting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 会议主题
     */
    @Excel(name = "会议主题")
    private String topic;

    /**
     * 主持人
     */
    @Excel(name = "主持人")
    private String host;

    /**
     * 会议地址
     */
    @Excel(name = "会议地址")
    private String place;

    /**
     * 参会人员
     */
    @Excel(name = "参会人员")
    private String peoplelist;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 会议纪要
     */
    @Excel(name = "会议纪要")
    private String content;


}
