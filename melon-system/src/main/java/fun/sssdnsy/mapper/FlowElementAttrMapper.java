package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.FlowElementAttr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FlowElementAttrMapper {
    /**
     * 查询
     */

    FlowElementAttr getFlowElementFormAttr(FlowElementAttr flowElementAttr);

    /**
     * 新增
     */
    int insert(FlowElementAttr flowElementAttr);

    /**
     * 修改
     */
    int update(FlowElementAttr flowElementAttr);

    /**
     * 删除
     */
    int delete(String id);

}
