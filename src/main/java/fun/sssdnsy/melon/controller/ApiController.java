package fun.sssdnsy.melon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.controller.ApiController
 * @desc
 * @since 2022-07-24
 */
@RestController("/api")
public class ApiController {


    @GetMapping(value = "/1")
    public void userPage() {
        int number = 1 / 0;
    }

}
