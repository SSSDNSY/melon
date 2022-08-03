package fun.sssdnsy.melon.controller;

import fun.sssdnsy.melon.dao.entity.User;
import fun.sssdnsy.melon.dao.repository.UserRepository;
import fun.sssdnsy.melon.pojo.dto.ResultDto;
import fun.sssdnsy.melon.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengzh
 * @desc
 * @class UserController
 * @since 2022-07-26
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/user/list")
    public ResultDto<List<User>> userList() {
        return ResultUtil.success(userRepository.findAll());
    }

}
