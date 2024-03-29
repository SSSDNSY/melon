package fun.sssdnsy.web.controller.system;

import fun.sssdnsy.config.Config;
import fun.sssdnsy.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 首页
 *
 * @author sssdnsy
 */
@RestController
public class SysIndexController {
    /**
     * 系统基础配置
     */
    @Resource
    private Config Config;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", Config.getName(), Config.getVersion());
    }
}
