package fun.sssdnsy.web.controller.config;

import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.XxlConfNode;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.service.IXxlConfNodeService;
import fun.sssdnsy.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private IXxlConfNodeService confNodeService;

    /**
     * 获取配置列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('config:conf:list')")
    public TableDataInfo list(XxlConfNode confNode) {
        startPage();
        List<XxlConfNode> list = confNodeService.list(confNode);
        return getDataTable(list);
    }


    /**
     * 查询环境、项目的下拉框
     * @return
     */
    @GetMapping("/listEnvApp")
    @PreAuthorize("@ss.hasPermi('config:conf:list')")
    public AjaxResult listEnvApp() {
        Map<String,List> dataMap = confNodeService.listEnvApp();
        return success(dataMap);
    }

    /**
     * 导出配置项
     */
    @Log(title = "导出配置项", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('config:conf:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XxlConfNode confNode) {
        List<XxlConfNode> list = confNodeService.list(confNode);
        ExcelUtil<XxlConfNode> util = new ExcelUtil(XxlConfNode.class);
        util.exportExcel(response, list, "参数数据");
    }

    /**
     * 根据evn,key获取配置项详细信息
     */
    @GetMapping(value = "/{env}/{key}")
    public AjaxResult getInfo(@PathVariable String env, @PathVariable String key) {
        XxlConfNode confNode = XxlConfNode.builder().env(env).key(key).build();
        return success(confNodeService.get(confNode));
    }


    /**
     * 保存修改配置项
     */
    @Log(title = "保存配置项", businessType = BusinessType.SAVE)
    @PreAuthorize("@ss.hasPermi('config:conf:edit')")
    @PostMapping
    public AjaxResult save(@Validated @RequestBody XxlConfNode confNode) {
        if (confNodeService.exist(confNode)) {
            return toAjax(confNodeService.update(confNode));
        }
        return toAjax(confNodeService.add(confNode));
    }

    /**
     * 修改配置项
     */
    @PreAuthorize("@ss.hasPermi('config:conf:edit')")
    @Log(title = "修改配置项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody XxlConfNode confNode) {
        if (confNodeService.exist(confNode)) {
            return error("修改配置项'" + confNode.getAppname() + "'失败，参数键名已存在");
        }
        return toAjax(confNodeService.update(confNode));
    }

    /**
     * 删除配置项
     */
    @PreAuthorize("@ss.hasPermi('config:conf:edit')")
    @Log(title = "删除配置项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{env}/{key}")
    public AjaxResult remove(@PathVariable String env, @PathVariable String key) {
        XxlConfNode confNode = XxlConfNode.builder().env(env).key(key).build();
        int delete = confNodeService.delete(confNode);
        return delete > 0 ? success() : error();
    }

}
