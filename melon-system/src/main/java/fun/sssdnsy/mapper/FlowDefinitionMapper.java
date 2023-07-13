package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.FlowDefinition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FlowDefinitionMapper {
    /**
     * 更新流程信息
     */
    int updateFlowEntity(FlowDefinition flowEntity);

    /**
     * 获取流程发起列表
     *
     * @param flowSort
     * @return
     */
    List<FlowDefinition> getFlowListBySort(String flowSort);

    /**
     * 获取常用的流程
     *
     * @param username
     * @return
     */
    List<FlowDefinition> getCommProceList(@Param("username") String username);

    /**
     * 根据id获取processins
     */
    FlowDefinition getProcessIns(String id);

    /**
     * 根据key获取最新版本的processins
     */
    FlowDefinition getProcessInsLastVersion(String key);

    /**
     * 根据deploymentid获取processins
     */
    FlowDefinition getProcessInsByDeployId(String deploymentid);

    int updateProcessEntity(FlowDefinition processEntity);


}
