package fun.sssdnsy.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.sssdnsy.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务规则对象 act_business_rule
 *
 * @author gssong
 * @date 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("act_business_rule")
public class ActBusinessRule extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long   id;
    /**
     * bean名称
     */
    private String beanName;
    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String param;

    /**
     * 备注
     */
    private String remark;

}
