package fun.sssdnsy.web.controller.form;

import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.FormMeta;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.service.IFormMetaService;
import fun.sssdnsy.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 表单Controller
 *
 * @author melon
 * @date 2023-07-06
 */
@RestController
@RequestMapping("/formMeta")
public class FormMetaController extends BaseController {
    @Resource
    private IFormMetaService formMetaService;

    /**
     * 查询表单列表
     */
    @PreAuthorize("@ss.hasPermi('sssdnsy:meta:list')")
    @GetMapping("/list")
    public TableDataInfo list(FormMeta formMeta) {
        startPage();
        List<FormMeta> list = formMetaService.selectFormMetaList(formMeta);
        return getDataTable(list);
    }

    /**
     * 导出表单列表
     */
    @PreAuthorize("@ss.hasPermi('sssdnsy:meta:export')")
    @Log(title = "表单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FormMeta formMeta) {
        List<FormMeta>      list = formMetaService.selectFormMetaList(formMeta);
        ExcelUtil<FormMeta> util = new ExcelUtil<FormMeta>(FormMeta.class);
        util.exportExcel(response, list, "表单数据");
    }

    /**
     * 获取表单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sssdnsy:meta:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(formMetaService.selectFormMetaById(id));
    }

    /**
     * 保存表单
     */
    @PreAuthorize("@ss.hasPermi('sssdnsy:meta:add')")
    @Log(title = "表单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult save(@RequestBody FormMeta formMeta) {
        return toAjax(formMetaService.insertFormMeta(formMeta));
    }

    /**
     * 删除表单
     */
    @PreAuthorize("@ss.hasPermi('sssdnsy:meta:remove')")
    @Log(title = "表单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(formMetaService.deleteFormMetaByIds(ids));
    }
}
