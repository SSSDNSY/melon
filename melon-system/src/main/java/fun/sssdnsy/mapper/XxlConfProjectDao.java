package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.XxlConfProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/10/8.
 */
@Mapper
public interface XxlConfProjectDao {

    List<XxlConfProject> list(XxlConfProject xxlConfProject);

    List<XxlConfProject> findAll();

    int save(XxlConfProject xxlConfProject);

    int update(XxlConfProject xxlConfProject);

    int delete(@Param("appname") String appname);

    XxlConfProject load(@Param("appname") String appname);

}
