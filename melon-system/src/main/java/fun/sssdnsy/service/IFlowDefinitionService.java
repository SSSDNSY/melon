package fun.sssdnsy.service;

import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.FlowDefinition;
import fun.sssdnsy.domain.FlowElementAttr;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * @Desc  流程定义
 * @Author pengzh
 * @Since 2023-07-13
 */
public interface IFlowDefinitionService {

    TableDataInfo list(FlowDefinition flowDefinition);

    FlowElementAttr get(String processKey);

    Integer save(FlowDefinition flowDefinition);

    Integer remove(String[] ids);

}
