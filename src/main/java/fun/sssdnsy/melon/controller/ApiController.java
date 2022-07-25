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
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.controller.ApiController
 * @desc
 * @since 2022-07-24
 */
@Controller
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"/", "/dashboard"})
    public String Home() {
        return "dashboard";
    }

    @GetMapping(value = "/user/list")
    @ResponseBody
    public ResultDto<List<User>> List() {
        return ResultUtil.success(userRepository.findAll());
    }


}
