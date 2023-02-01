package fun.sssdnsy.web.controller.config;

import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.XxlConfNode;
import fun.sssdnsy.domain.XxlConfProject;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.mapper.XxlConfProjectDao;
import fun.sssdnsy.service.IXxlConfNodeService;
import fun.sssdnsy.service.IXxlConfProjectService;
import fun.sssdnsy.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 配置管理
 *
 * @author xuxueli
 */
@RestController
@RequestMapping("/config/conf")
public class ConfController extends BaseController {


    @Autowired
    private IXxlConfProjectService confProjectService;

    /**
     * 获取参数配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(XxlConfProject project) {
        startPage();
        List<XxlConfProject> list = confProjectService.selectConfigList(project);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('config:project:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XxlConfProject project) {
        List<XxlConfProject> list = confProjectService.selectConfigList(project);
        ExcelUtil<XxlConfProject> util = new ExcelUtil<XxlConfProject>(XxlConfProject.class);
        util.exportExcel(response, list, "参数数据");
    }

    /**
     * 根据参数编号获取详细信息
     */
    @GetMapping(value = "/{appName}")
    public AjaxResult getInfo(@PathVariable String appName) {
        return success(confProjectService.selectConfigByName(appName));
    }


    /**
     * 保存参数配置
     */
    @Log(title = "参数管理", businessType = BusinessType.SAVE)
    @PostMapping
    public AjaxResult save(@Validated @RequestBody XxlConfProject project) {
        if (confProjectService.checkConfigKeyUnique(project)) {
            return toAjax(confProjectService.updateConfig(project));
        }
        return toAjax(confProjectService.insertConfig(project));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermi('config:project:edit')")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody XxlConfProject project) {
        if (confProjectService.checkConfigKeyUnique(project)) {
            return error("修改参数'" + project.getAppname() + "'失败，参数键名已存在");
        }
        return toAjax(confProjectService.updateConfig(project));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermi('config:project:remove')")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{appname}")
    public AjaxResult remove(@PathVariable  String appname) {
        confProjectService.deleteConfigByName(new String[]{appname});
        return success();
    }

}
