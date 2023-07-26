package controller;

import domain.bo.ActDynamicFormBo;
import domain.vo.ActDynamicFormVo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.annotation.RepeatSubmit;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.domain.R;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.core.validate.EditGroup;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.IActDynamicFormService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 动态表单Controller
 *
 * @author gssong
 * @date 2022-08-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/dynamicForm")
public class ActDynamicFormController extends BaseController {

    private final IActDynamicFormService iActDynamicFormService;

    /**
     * 查询动态表单列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:dynamicForm:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActDynamicFormBo bo, PageQuery pageQuery) {
        return iActDynamicFormService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询启用动态表单列表
     */
    @GetMapping("/enableList")
    public TableDataInfo enableList(ActDynamicFormBo bo, PageQuery pageQuery) {
        return iActDynamicFormService.queryPageEnableList(bo, pageQuery);
    }

    /**
     * 导出动态表单列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:dynamicForm:export')")
    @Log(title = "动态表单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ActDynamicFormBo bo, HttpServletResponse response) {
        List<ActDynamicFormVo> list = iActDynamicFormService.queryList(bo);
        ExcelUtil.exportExcel(list, "动态表单", ActDynamicFormVo.class, response);
    }

    /**
     * 获取动态表单详细信息
     */
    @PreAuthorize("@ss.hasPermi('workflow:dynamicForm:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable("id") Long id) {
        return AjaxResult.success(iActDynamicFormService.queryById(id));
    }

    /**
     * 新增动态表单
     */
    @PreAuthorize("@ss.hasPermi('workflow:dynamicForm:add')")
    @Log(title = "动态表单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody ActDynamicFormBo bo) {
        return toAjax(iActDynamicFormService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改动态表单
     */
    @PreAuthorize("@ss.hasPermi('workflow:dynamicForm:edit')")
    @Log(title = "动态表单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody ActDynamicFormBo bo) {
        return toAjax(iActDynamicFormService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 修改动态表单
     */
    @PreAuthorize("@ss.hasPermi('workflow:dynamicForm:edit')")
    @Log(title = "动态表单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/editForm")
    public AjaxResult editForm(@RequestBody ActDynamicFormBo bo) {
        return toAjax(iActDynamicFormService.editForm(bo) ? 1 : 0);
    }

    /**
     * 删除动态表单
     */
    @PreAuthorize("@ss.hasPermi('workflow:dynamicForm:remove')")
    @Log(title = "动态表单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(iActDynamicFormService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
