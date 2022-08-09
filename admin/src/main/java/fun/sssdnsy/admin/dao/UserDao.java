package fun.sssdnsy.admin.dao;

import fun.sssdnsy.admin.core.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuxueli 2018-03-01
 */
@Mapper
public interface UserDao {

    public List<User> pageList(@Param("offset") int offset,
                               @Param("pagesize") int pagesize,
                               @Param("username") String username,
                               @Param("permission") int permission);
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("username") String username,
                             @Param("permission") int permission);

    public int add(User user);

    public int update(User user);

    public int delete(@Param("username") String username);

    public User load(@Param("username") String username);

}
