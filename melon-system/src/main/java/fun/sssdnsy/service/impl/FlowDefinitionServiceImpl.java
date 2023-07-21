package fun.sssdnsy.service.impl;

import fun.sssdnsy.constant.HttpStatus;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.FlowDefinition;
import fun.sssdnsy.domain.FlowElementAttr;
import fun.sssdnsy.domain.FlowSort;
import fun.sssdnsy.mapper.FlowDefinitionMapper;
import fun.sssdnsy.mapper.FlowElementAttrMapper;
import fun.sssdnsy.service.IFlowDefinitionService;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc 流程定义
 * @Author pengzh
 * @Since 2023-07-13
 */
@Service
public class FlowDefinitionServiceImpl implements IFlowDefinitionService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private FlowDefinitionMapper flowDefinitionMapper;

    @Resource
    private FlowElementAttrMapper flowElementAttrMapper;

    @Override
    public TableDataInfo list(FlowDefinition flowDefinition) {

        TableDataInfo tableDataInfo = new TableDataInfo();

        int startIndex = (flowDefinition.getPageNumber() - 1) * flowDefinition.getPageSize();
        int maxIndex   = startIndex + flowDefinition.getPageSize();

        long count = repositoryService.createProcessDefinitionQuery()
                .latestVersion().count();
        if(count<1){
            return tableDataInfo;
        }
        List<ProcessDefinition> list =  repositoryService.createProcessDefinitionQuery()
                .latestVersion()
                .listPage(startIndex, maxIndex);
        List<FlowDefinition> flowDefinitions = new ArrayList<>();
        for(ProcessDefinition definition:list){
            FlowDefinition flow = new FlowDefinition();
            BeanUtils.copyProperties(definition,flow);
            flowDefinitions.add(flow);
        }
        flowDefinitions.sort((y, x) -> x.getVersion() - y.getVersion());

        tableDataInfo.setCode(HttpStatus.SUCCESS);
        tableDataInfo.setRows(flowDefinitions);
        tableDataInfo.setTotal(count);
        return tableDataInfo;
    }

    @Override
    public FlowElementAttr get(String processKey) {
        // 获取流程模版信息
        FlowDefinition flowDefinition = flowDefinitionMapper.getProcessInsLastVersion(processKey);
        BpmnModel      bpmnModel      = repositoryService.getBpmnModel(flowDefinition.getId());
        // 获取流程信息
        Process            process       = bpmnModel.getProcesses().get(0);
        StartEvent         startEvent    = (StartEvent) process.getInitialFlowElement();
        List<SequenceFlow> outgoingFlows = startEvent.getOutgoingFlows();
        // 获取UserTask类型数据
        UserTask        userTask  = (UserTask) outgoingFlows.get(0).getTargetFlowElement();
        FlowElementAttr attrParam = new FlowElementAttr();
        attrParam.setTaskDefKey(userTask.getId());
        FlowElementAttr flowElementAttr = flowElementAttrMapper.getFlowElementFormAttr(attrParam);
        return flowElementAttr;
    }

    @Override
    public Integer save(FlowDefinition flowDefinition) {
        // 更新流程部署信息，将引擎版本号作为流程类别
        flowDefinitionMapper.updateFlowEntity(flowDefinition);

        FlowDefinition  dbDefinition    = flowDefinitionMapper.getProcessInsByDeployId(flowDefinition.getDeploymentId());
        FlowElementAttr flowElementAttr = new FlowElementAttr();
        flowElementAttr.setProcessKey(dbDefinition.getKey());
        flowElementAttr.setFormId(flowDefinition.getFormId());
        flowElementAttr.setFormUrl(flowDefinition.getFormUrl());
        int rows = flowElementAttrMapper.update(flowElementAttr);

        return rows;
    }

    @Override
    public Integer remove(String[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return -1;
        }
        for (String deploymentId : ids) {
            repositoryService.deleteDeployment(deploymentId, true);
        }
        return ids.length;
    }

    @Override
    public void deployWithBPMNJS(String stringBPMNXml) {


    }

    @Override
    public void getResourceAsStream(HttpServletResponse response, String deploymentId, String resourceName) throws IOException {
        InputStream inputStream = repositoryService.getResourceAsStream(deploymentId,resourceName);
        int         count       = inputStream.available();
        byte[]      bytes       = new byte[count];
        response.setContentType("text/xml");
        OutputStream outputStream = response.getOutputStream();
        while (inputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
    }

}
