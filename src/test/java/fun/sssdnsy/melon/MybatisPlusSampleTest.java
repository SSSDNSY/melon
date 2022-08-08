package fun.sssdnsy.melon;

import fun.sssdnsy.melon.dao.entity.UserEntity;
import fun.sssdnsy.melon.dao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @desc
 * @class My
 * @since 2022-08-08
 */
@SpringBootTest
class MybatisPlusSampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserEntity> userEntityList = userMapper.selectList(null);
        userEntityList.forEach(System.out::println);
    }
}