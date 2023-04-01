package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.XxlConfNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/10/8.
 */
@Mapper
public interface XxlConfNodeDao {

    List<XxlConfNode> list(XxlConfNode xxlConfNode);

    int delete(@Param("env") String env, @Param("key") String key);

    int insert(XxlConfNode xxlConfNode);

    XxlConfNode load(@Param("env") String env, @Param("key") String key);

    int update(XxlConfNode xxlConfNode);

}
