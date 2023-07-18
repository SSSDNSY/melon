package fun.sssdnsy.web.controller.system;

import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.service.ISysHomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 主页信息接口
 *
 * @author sssdnsy
 */
@RestController
@RequestMapping("/system/data")
public class SysDataSyncController extends BaseController {
    @Resource
    private ISysHomeService homeInfoService;

    /**
     * 获取参数配置列表
     */
    @GetMapping("/list")
    public AjaxResult list() {
        return AjaxResult.success(homeInfoService.list());
    }

}
