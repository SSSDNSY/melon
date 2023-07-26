package domain.bo;

import domain.ActBusinessRuleParam;
import fun.sssdnsy.core.domain.BaseEntity;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 业务规则业务对象 act_business_rule
 *
 * @author gssong
 * @date 2021-12-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ActBusinessRuleBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 全类名
     */
    @NotBlank(message = "bean名称为空", groups = {AddGroup.class, EditGroup.class})
    private String beanName;

    /**
     * 方法名
     */
    @NotBlank(message = "方法名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String method;

    /**
     * 参数
     */
    private String param;

    /**
     * 备注
     */
    private String remark;

    /**
     * 参数
     */
    private List<ActBusinessRuleParam> businessRuleParams;

}
