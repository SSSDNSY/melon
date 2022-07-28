package fun.sssdnsy.melon.controller;

import fun.sssdnsy.melon.dao.entity.User;
import fun.sssdnsy.melon.dao.repository.UserRepository;
import fun.sssdnsy.melon.pojo.dto.ResultDto;
import fun.sssdnsy.melon.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author pengzh
 * @desc
 * @class UserController
 * @since 2022-07-26
 */
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/user")
    public String userPage() {
        return "/pages/userList";
    }

    @GetMapping(value = "/user/list")
    @ResponseBody
    public ResultDto<List<User>> userList() {
        return ResultUtil.success(userRepository.findAll());
    }

}
