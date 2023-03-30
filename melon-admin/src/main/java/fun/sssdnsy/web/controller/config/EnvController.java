package fun.sssdnsy.web.controller.config;

import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.XxlConfEnv;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.service.IXxlConfEnvService;
import fun.sssdnsy.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 环境管理
 *
 * @author xuxueli 2018-05-30
 */
@RestController
@RequestMapping("/config/env")
public class EnvController extends BaseController {

    @Autowired
    private IXxlConfEnvService confEnvService;

    /**
     * 获取环境配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(XxlConfEnv env) {
        startPage();
        List<XxlConfEnv> list = confEnvService.selectConfigList(env);
        return getDataTable(list);
    }

    @Log(title = "导出环境配置", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('config:env:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XxlConfEnv env) {
        List<XxlConfEnv> list = confEnvService.selectConfigList(env);
        ExcelUtil<XxlConfEnv> util = new ExcelUtil<XxlConfEnv>(XxlConfEnv.class);
        util.exportExcel(response, list, "导出环境配置");
    }

    /**
     * 根据参数编号获取环境配置详细信息
     */
    @GetMapping(value = "/{env}")
    public AjaxResult getInfo(@PathVariable String env) {
        return success(confEnvService.selectByName(env));
    }


    /**
     * 保存环境配置
     */
    @Log(title = "保存环境配置", businessType = BusinessType.SAVE)
    @PostMapping
    public AjaxResult save(@Validated @RequestBody XxlConfEnv env) {
        if (confEnvService.checkConfigKeyUnique(env)) {
            return toAjax(confEnvService.updateConfig(env));
        }
        return toAjax(confEnvService.insertConfig(env));
    }

    /**
     * 修改环境配置
     */
    @PreAuthorize("@ss.hasPermi('config:env:edit')")
    @Log(title = "修改环境", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody XxlConfEnv env) {
        if (confEnvService.checkConfigKeyUnique(env)) {
            return error("修改环境'" + env.getEnv() + "'失败，环境名已存在");
        }
        return toAjax(confEnvService.updateConfig(env));
    }

    /**
     * 删除环境配置
     */
    @PreAuthorize("@ss.hasPermi('config:env:remove')")
    @Log(title = "删除环境配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{appname}")
    public AjaxResult remove(@PathVariable String appname) {
        confEnvService.deleteConfigByName(new String[]{appname});
        return success();
    }

}
