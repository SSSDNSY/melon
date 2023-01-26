package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.XxlConfNodeLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/10/8.
 */
@Mapper
public interface XxlConfNodeLogDao {

    List<XxlConfNodeLog> findByKey(@Param("env") String env, @Param("key") String key);

    void add(XxlConfNodeLog xxlConfNode);

    int deleteTimeout(@Param("env") String env,
                      @Param("key") String key,
                      @Param("length") int length);

}
