package fun.sssdnsy.melon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.controller.ApiController
 * @desc
 * @since 2022-07-24
 */
@RestController
public class ApiController {

    @GetMapping(value = "/test")
    public String Home() {
        return "ok";
    }

}
