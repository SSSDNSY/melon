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

    /**
     * home page
     *
     * @return
     */
    @GetMapping(value = {"/", "/home", "index"})
    public String Home() {
        return "index";
    }

    /**
     * User List use dataTables.js
     *
     * @return
     */
    @GetMapping(value = "/user1")
    public String user1() throws Exception {
        return "/pages/user1";
    }

    /**
     * User List use jsGrid.js
     *
     * @return
     */
    @GetMapping(value = "/user2")
    public String user2() throws Exception {
        return "/pages/user2";
    }

    /**
     * error test
     *
     * @return
     */
    @GetMapping(value = "/err")
    public String error() throws Exception {
        return "" + (1 / 0);
    }


}
