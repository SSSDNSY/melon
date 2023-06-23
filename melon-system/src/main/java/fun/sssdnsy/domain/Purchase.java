package fun.sssdnsy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import fun.sssdnsy.annotation.Excel;
import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 采购对象 purchase
 *
 * @author shenzhanwang
 * @date 2022-05-28
 */
@Getter
@Setter
@ToString
public class Purchase extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    @Excel(name = "物品列表")
    private String itemlist;

    /**
     *
     */
    @Excel(name = "总价")
    private String total;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date applytime;

    /**
     *
     */
    @Excel(name = "申请人")
    private String applyer;

    // 采购经理
    private String purchasemanager;

    // 财务
    private String finance;

    // 出纳
    private String pay;

    // 总经理
    private String manager;

}
