package fun.sssdnsy.domain;

import fun.sssdnsy.annotation.Excel;
import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 表单对象 form_meta
 *
 * @author melon
 * @date 2023-07-06
 */
@Getter
@Setter
@ToString
public class FormMeta extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 事件类型
     */
    @Excel(name = "事件类型")
    private String eventType;

    /**
     * 事件执行方式
     */
    @Excel(name = "事件执行方式")
    private String eventExe;

    /**
     * 主表
     */
    @Excel(name = "主表")
    private String mainTable;

    /**
     * 子表样式属性（其他样式扩展）
     */
    @Excel(name = "子表样式属性", readConverterExp = "其=他样式扩展")
    private String subTable;

    /**
     * 表单JS
     */
    @Excel(name = "表单JS")
    private String formJs;

    /**
     * 表单HTML
     */
    @Excel(name = "表单HTML")
    private String formHtml;

    /**
     * 表单JSON
     */
    @Excel(name = "表单JSON")
    private String formJson;

    /**
     * 是否主版本（Y是 N否）
     */
    @Excel(name = "是否主版本", readConverterExp = "Y=是,N=否")
    private String isMain;

    /**
     * 版本号
     */
    @Excel(name = "版本号")
    private Long version;

}
