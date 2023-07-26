package controller;


import domain.bo.ActBusinessRuleBo;
import domain.vo.ActBusinessRuleVo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.annotation.RepeatSubmit;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.domain.R;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.core.validate.EditGroup;
import fun.sssdnsy.core.validate.QueryGroup;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.IActBusinessRuleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 业务规则Controller
 *
 * @author gssong
 * @date 2021-12-16
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Resource)
@RestController
@RequestMapping("/workflow/businessRule")
public class ActBusinessRuleController extends BaseController {

    private final IActBusinessRuleService iActBusinessRuleService;

    /**
     * 查询业务规则列表
     */
    // @SaCheckPermission("workflow:businessRule:list")
    @GetMapping("/list")
    public TableDataInfo list(@Validated(QueryGroup.class) ActBusinessRuleBo bo, PageQuery pageQuery) {
        return iActBusinessRuleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出业务规则列表
     */
    // @SaCheckPermission("workflow:businessRule:export")
    @Log(title = "业务规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated ActBusinessRuleBo bo, HttpServletResponse response) {
        List<ActBusinessRuleVo> list = iActBusinessRuleService.queryList(bo);
        ExcelUtil.exportExcel(list, "业务规则", ActBusinessRuleVo.class, response);
    }

    /**
     * 获取业务规则详细信息
     */
    // @SaCheckPermission("workflow:businessRule:query")
    @GetMapping("/{id}")
    public R<ActBusinessRuleVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(iActBusinessRuleService.queryById(id));
    }

    /**
     * 新增业务规则
     */
    // @SaCheckPermission("workflow:businessRule:add")
    @Log(title = "业务规则", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody ActBusinessRuleBo bo) {
        return toAjax(iActBusinessRuleService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改业务规则
     */
    // @SaCheckPermission("workflow:businessRule:edit")
    @Log(title = "业务规则", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody ActBusinessRuleBo bo) {
        return toAjax(iActBusinessRuleService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除业务规则
     */
    // @SaCheckPermission("workflow:businessRule:remove")
    @Log(title = "业务规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(iActBusinessRuleService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
