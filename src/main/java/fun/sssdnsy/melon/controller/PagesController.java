package fun.sssdnsy.melon.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author pengzh
 * @desc
 * @class PagesController
 * @since 2022-07-28
 */
public class PagesController {

    @GetMapping(value = {"/", "/home"})
    public String Home() {
        return "home";
    }

    @GetMapping(value = {"/error"})
    public String error() {
        return "" + (1 / 0);
    }


}
