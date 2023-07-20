package fun.sssdnsy.web.controller.system;

import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.service.ISysDataSyncService;
import org.springframework.web.bind.annotation.*;

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
    private ISysDataSyncService dataSyncService;

    /**
     * 获取数据源列表
     */
    @GetMapping("/list/{name}")
    public AjaxResult list(@PathVariable String name) {
        return AjaxResult.success(dataSyncService.listDataSource(name));
    }


    /**
     * 通用添加数据源
     */
    @PostMapping("/add")
    public AjaxResult add() {
        return AjaxResult.success(dataSyncService.addDataSource());
    }

    /**
     * 删除数据源
     */
    @DeleteMapping("/remove")
    public AjaxResult remove(String name) {
        dataSyncService.removeDataSource(name);
        return AjaxResult.success();
    }

}
