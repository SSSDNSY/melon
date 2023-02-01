package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.XxlConfEnv;
import fun.sssdnsy.domain.XxlConfProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 2018-05-30
 */
@Mapper
public interface XxlConfEnvDao {

    List<XxlConfEnv> list(XxlConfEnv env);

    XxlConfEnv getOne(@Param("env") String env);

    List<XxlConfEnv> findAll();

    int save(XxlConfEnv xxlConfEnv);

    int update(XxlConfEnv xxlConfEnv);

    int delete(@Param("env") String[]  env);

    XxlConfEnv load(@Param("env") String env);

}
