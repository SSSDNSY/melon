package fun.sssdnsy.activiti.cmd;

import cn.hutool.core.collection.CollectionUtil;
import fun.sssdnsy.utils.spring.SpringUtils;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntityManager;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: ruoyi-vue-plus
 * @description: 删除执行实例
 * @author: gssong
 * @created: 2022/4/21
 */
public class DeleteExecutionCmd implements Command<Void> {

    /**
     * 执行id
     */
    private final String executionId;

    public DeleteExecutionCmd(String executionId) {
        this.executionId = executionId;
    }

    @Override
    public Void execute(CommandContext commandContext) {
        // 获得用到的Manager
        ExecutionEntityManager        executionEntityManager        = commandContext.getExecutionEntityManager();
        VariableInstanceEntityManager variableInstanceEntityManager = commandContext.getVariableInstanceEntityManager();
        ExecutionEntity               execution                     = executionEntityManager.findById(executionId);
        TaskService                   taskService                   = SpringUtils.getBean(TaskService.class);
        List<Task>                    list                          = taskService.createTaskQuery().processInstanceId(execution.getProcessInstanceId()).list();
        if (CollectionUtil.isNotEmpty(list)) {
            List<ExecutionEntity> childExecutions = executionEntityManager.findChildExecutionsByParentExecutionId(execution.getParentId());
            for (ExecutionEntity childExecution : childExecutions) {
                List<String> collect = list.stream().map(Task::getExecutionId).collect(Collectors.toList());
                if (!collect.contains(childExecution.getId())) {
                    executionEntityManager.delete(childExecution);
                    List<VariableInstanceEntity> variableList = variableInstanceEntityManager.findVariableInstancesByExecutionId(childExecution.getId());
                    if (CollectionUtil.isNotEmpty(variableList)) {
                        for (VariableInstanceEntity variableInstanceEntity : variableList) {
                            variableInstanceEntityManager.delete(variableInstanceEntity);
                        }
                    }
                }
            }
        }
        return null;
    }
}
