package fun.sssdnsy.service.impl;

import fun.sssdnsy.domain.FlowSort;
import fun.sssdnsy.mapper.FlowSortMapper;
import fun.sssdnsy.service.IFlowSortService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Desc 流程分类实现
 * @Author pengzh
 * @Since 2023-07-13
 */
@Service
public class FlowSortServiceImpl implements IFlowSortService {

    @Resource
    private FlowSortMapper flowSortMapper;


    @Override
    public List<FlowSort> list(FlowSort flowSort) {
        return flowSortMapper.list(flowSort);
    }

    @Override
    public List<FlowSort> getAll() {
        return flowSortMapper.list(null);
    }

    @Override
    public Integer save(FlowSort flowSort) {
        if (flowSort.getId() == null) {
            flowSort.setParentId(flowSort.getParentId() == null ? 0L : flowSort.getParentId());
            return flowSortMapper.insert(flowSort);
        } else {
            return flowSortMapper.update(flowSort);
        }
    }

    @Override
    public List<FlowSort> getTree() {
        List<FlowSort> flowSortList = getAll();
        // 组装树形结构
        List<FlowSort> treeList = flowSortList.stream()
                .filter(t -> t.getParentId() == 0)
                .map((sort) -> {
                    sort.setChildren(getChildren(sort, flowSortList));
                    return sort;
                })
                .collect(Collectors.toList());

        return treeList;
    }

    /**
     * 递归查找当前分类的子分类
     *
     * @param root        单个对象
     * @param allFlowSort 所有的集合
     * @return 排序后的子类
     */
    private List<FlowSort> getChildren(FlowSort root, List<FlowSort> allFlowSort) {
        List<FlowSort> childrenList = allFlowSort.stream()
                .filter(t -> t.getParentId() == root.getId())
                .map(g -> {
                    // 找子分类
                    g.setChildren(getChildren(g, allFlowSort));
                    return g;
                })
                // 排序分类
                .collect(Collectors.toList());

        return childrenList;
    }


    @Override
    public Integer remove(Integer[] ids) {
        if (ArrayUtils.isNotEmpty(ids)) {
            AtomicInteger count = new AtomicInteger(0);
            Arrays.stream(ids).forEach(id -> {
                count.addAndGet(flowSortMapper.delete(id));
            });
            return count.get();
        }
        return null;
    }
}
