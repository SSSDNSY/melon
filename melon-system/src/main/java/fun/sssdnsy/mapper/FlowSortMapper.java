package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.FlowSort;

import java.util.List;

/**
 * 流程分类Mapper接口
 */
public interface FlowSortMapper {

    /**
     * 查询
     */
    List<FlowSort> list(FlowSort flowSort);

    /**
     * 新增
     */
    int insert(FlowSort flowSort);

    /**
     * 修改保存
     */
    int update(FlowSort flowSort);

    /**
     * 删除
     */
    int delete(Integer id);

}
