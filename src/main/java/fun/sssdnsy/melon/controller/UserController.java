package fun.sssdnsy.melon.controller;

import fun.sssdnsy.melon.dao.entity.User;
import fun.sssdnsy.melon.dao.repository.UserRepository;
import fun.sssdnsy.melon.pojo.condition.UserCondtion;
import fun.sssdnsy.melon.pojo.dto.ResultDto;
import fun.sssdnsy.melon.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pengzh
 * @desc
 * @class UserController
 * @since 2022-07-26
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/user/{id}")
    public ResultDto<User> detail(@PathVariable Long id) {
        return ResultUtil.success(userRepository.findById(id).get());
    }

    @GetMapping(path = "/user/page")
    public ResultDto<Page<User>> page(UserCondtion pageRequest) {
        return ResultUtil.success(userRepository.findAll(pageRequest));
    }

    @DeleteMapping(path = "/user/{id}")
    public ResultDto<List<User>> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResultUtil.success();
    }

    @PostMapping(path = "/user/save")
    public ResultDto save(@RequestBody User user) {
        return ResultUtil.success(userRepository.save(user));
    }

}
