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
    public TableDataInfo list(XxlConfNode confNode) {
        startPage();
        List<XxlConfNode> list = confNodeService.list(confNode);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('config:project:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XxlConfNode confNode) {
        List<XxlConfNode> list = confNodeService.list(confNode);
        ExcelUtil<XxlConfNode> util = new ExcelUtil(XxlConfNode.class);
        util.exportExcel(response, list, "参数数据");
    }

    /**
     * 根据evn,key获取详细信息
     */
    @GetMapping(value = "/{env}/{key}")
    public AjaxResult getInfo(@PathVariable String env, @PathVariable String key) {
        XxlConfNode confNode = XxlConfNode.builder().env(env).key(key).build();
        return success(confNodeService.get(confNode));
    }


    /**
     * 保存参数配置
     */
    @Log(title = "参数管理", businessType = BusinessType.SAVE)
    @PostMapping
    public AjaxResult save(@Validated @RequestBody XxlConfNode confNode) {
        if (confNodeService.exist(confNode)) {
            return toAjax(confNodeService.update(confNode));
        }
        return toAjax(confNodeService.add(confNode));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermi('config:project:edit')")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody XxlConfNode confNode) {
        if (confNodeService.exist(confNode)) {
            return error("修改参数'" + confNode.getAppname() + "'失败，参数键名已存在");
        }
        return toAjax(confNodeService.update(confNode));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermi('config:project:remove')")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{env}/{key}")
    public AjaxResult remove(@PathVariable String env, @PathVariable String key) {
        XxlConfNode confNode = XxlConfNode.builder().env(env).key(key).build();
        int delete = confNodeService.delete(confNode);
        return delete > 0 ? success() : error();
    }

}
