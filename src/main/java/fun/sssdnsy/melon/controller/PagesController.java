package fun.sssdnsy.melon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author pengzh
 * @desc
 * @class PagesController
 * @since 2022-07-28
 */
@Controller
public class PagesController {

    @GetMapping(value = {"/", "/home"})
    public String Home() {
        return "home";
    }


}
