package fun.sssdnsy.service;

import fun.sssdnsy.domain.FlowSort;

import java.util.List;

/**
 * @Desc 流程分类接口
 * @Author pengzh
 * @Since 2023-07-13
 */
public interface IFlowSortService {


    List<FlowSort> list(FlowSort flowSort);

    List<FlowSort> getAll();

    Integer save(FlowSort flowSort);

    List<FlowSort> getTree();

    Integer remove(Integer[] ids);

}
