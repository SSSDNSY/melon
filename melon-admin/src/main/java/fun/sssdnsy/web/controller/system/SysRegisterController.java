package fun.sssdnsy.web.controller.system;

import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.model.RegisterBody;
import fun.sssdnsy.service.ISysConfigService;
import fun.sssdnsy.utils.StringUtils;
import fun.sssdnsy.web.service.SysRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 注册验证
 *
 * @author sssdnsy
 */
@RestController
public class SysRegisterController extends BaseController {
    @Resource
    private SysRegisterService registerService;

    @Resource
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
