package fun.sssdnsy.controller;

import fun.sssdnsy.domain.bo.ActBusinessFormBo;
import fun.sssdnsy.domain.vo.ActBusinessFormVo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.annotation.RepeatSubmit;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.core.validate.EditGroup;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fun.sssdnsy.service.IActBusinessFormService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 业务表单Controller
 *
 * @author gssong
 * @date 2022-08-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/businessForm")
public class ActBusinessFormController extends BaseController {

    private final IActBusinessFormService iActBusinessFormService;

    /**
     * 查询业务表单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ActBusinessFormBo bo, PageQuery pageQuery) {
        return iActBusinessFormService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出业务表单列表
     */
    @Log(title = "业务表单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ActBusinessFormBo bo, HttpServletResponse response) {
        List<ActBusinessFormVo> list = iActBusinessFormService.queryList(bo);
        ExcelUtil.exportExcel(list, "业务表单", ActBusinessFormVo.class, response);
    }

    /**
     * 获取业务表单详细信息
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                        @PathVariable("id") Long id) {
        return AjaxResult.success(iActBusinessFormService.queryById(id));
    }

    /**
     * 新增业务表单
     */
    // @SaCheckPermission("workflow:businessForm:add")
    @Log(title = "业务表单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody ActBusinessFormBo bo) {
        return AjaxResult.success(iActBusinessFormService.insertByBo(bo));
    }

    /**
     * 修改业务表单
     */
    // @SaCheckPermission("workflow:businessForm:edit")
    @Log(title = "业务表单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody ActBusinessFormBo bo) {
        return AjaxResult.success(iActBusinessFormService.updateByBo(bo));
    }

    /**
     * 删除业务表单
     */
    // @SaCheckPermission("workflow:businessForm:remove")
    @Log(title = "业务表单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(Boolean.TRUE.equals(iActBusinessFormService.deleteWithValidByIds(Arrays.asList(ids))) ? 1 : 0);
    }
}
