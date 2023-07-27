package fun.sssdnsy.activiti.factory;

import org.activiti.engine.*;

import javax.annotation.Resource;


/**
 * 统一管理activity提供的服务接口
 */
public class WorkflowService {


    /**
     * 流程引擎
     */
    @Resource
    public ProcessEngine processEngine;

    /**
     * 流程仓库服务类
     */
    @Resource
    public RepositoryService repositoryService;

    /**
     * 查询运行信息
     */
    @Resource
    public RuntimeService runtimeService;

    /**
     * 查询任务信息
     */
    @Resource
    public TaskService taskService;

    /**
     * 查询历史信息
     */
    @Resource
    public HistoryService historyService;

}
