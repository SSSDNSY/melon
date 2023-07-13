package fun.sssdnsy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-07-13
 */
@Getter
@Setter
@ToString
public class FlowDefinition extends ProcessDefinitionEntityImpl {

    // deploymentId
    private String id;
    // 流程名称
    private String name;
    // 流程分类
    private String engineVersion;
    // 流程实例Key
    private String processKey;

    // 流程分类
    private String formId;
    // 流程实例Key
    private String formUrl;

    private Integer pageSize;

    private Integer pageNumber;
    protected boolean isIdentityLinksInitialized = true;

    @JsonIgnore
    @Override
    public List<IdentityLinkEntity> getIdentityLinks() {
        return Lists.newArrayList();
    }

    public void setIdentityLinks() {
    }

    public boolean isIdentityLinksInitialized() {
        return true;
    }

    public FlowDefinition setIdentityLinksInitialized(boolean identityLinksInitialized) {
        isIdentityLinksInitialized = true;
        return this;
    }


}
