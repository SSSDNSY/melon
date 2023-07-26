package controller;

import domain.bo.ActProcessDefSettingBo;
import domain.vo.ActProcessDefSettingVo;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.IActProcessDefSetting;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 流程定义设置Controller
 *
 * @author gssong
 * @date 2022-08-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/processDefSetting")
public class ActProcessDefSettingController extends BaseController {

    private final IActProcessDefSetting iActProcessDefSetting;

    /**
     * 查询流程定义设置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ActProcessDefSettingBo bo, PageQuery pageQuery) {
        return iActProcessDefSetting.queryPageList(bo, pageQuery);
    }

    /**
     * 导出流程定义设置列表
     */
    @Log(title = "流程定义设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ActProcessDefSettingBo bo, HttpServletResponse response) {
        List<ActProcessDefSettingVo> list = iActProcessDefSetting.queryList(bo);
        ExcelUtil.exportExcel(list, "流程定义设置", ActProcessDefSettingVo.class, response);
    }

    /**
     * 获取流程定义设置详细信息
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(iActProcessDefSetting.queryById(id));
    }

    /**
     * 按流程定义id查询流程定义设置详细
     */
    @GetMapping("/getProcessDefSettingByDefId/{defId}")
    public AjaxResult getProcessDefSettingByDefId(@NotNull(message = "流程定义id不能为空") @PathVariable("defId") String defId) {
        return AjaxResult.success(iActProcessDefSetting.getProcessDefSettingByDefId(defId));
    }

    /**
     * 校验表单是否关联
     */
    @GetMapping("/checkProcessDefSetting/{defId}/{param}/{businessType}")
    public AjaxResult checkProcessDefSetting(@NotNull(message = "流程定义id不能为空") @PathVariable("defId") String defId,
                                                @NotNull(message = "参数不能为空") @PathVariable("param") String param,
                                                @NotNull(message = "业务类型") @PathVariable("businessType") Integer businessType) {
        return iActProcessDefSetting.checkProcessDefSetting(defId, param, businessType);
    }

    /**
     * 新增流程定义设置
     */
    @Log(title = "流程定义设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody ActProcessDefSettingBo bo) {
        return toAjax(iActProcessDefSetting.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改流程定义设置
     */
    @Log(title = "流程定义设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody ActProcessDefSettingBo bo) {
        return toAjax(iActProcessDefSetting.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除流程定义设置
     */
    @Log(title = "流程定义设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(iActProcessDefSetting.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
