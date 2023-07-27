package fun.sssdnsy.controller;

import fun.sssdnsy.domain.bo.SysMessageBo;
import fun.sssdnsy.domain.vo.SysMessageVo;
import fun.sssdnsy.annotation.Log;
import fun.sssdnsy.annotation.RepeatSubmit;
import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.domain.PageQuery;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.core.validate.AddGroup;
import fun.sssdnsy.core.validate.EditGroup;
import fun.sssdnsy.core.validate.QueryGroup;
import fun.sssdnsy.enums.BusinessType;
import fun.sssdnsy.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fun.sssdnsy.service.ISysMessageService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 消息通知Controller
 *
 * @author gssong
 * @date 2022-06-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/message")
public class SysMessageController extends BaseController {

    private final ISysMessageService iSysMessageService;

    /**
     * 查询消息通知列表
     */
    // @SaCheckPermission("workflow:message:list")
    @GetMapping("/list")
    public TableDataInfo list(@Validated(QueryGroup.class) SysMessageBo bo, PageQuery pageQuery) {
        return iSysMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询消息通知列表
     */
    
    @GetMapping("/waitingMessage")
    public TableDataInfo message() {
        return iSysMessageService.queryPage();
    }

    /**
     * 导出消息通知列表
     */
    // @SaCheckPermission("workflow:message:export")
    @Log(title = "消息通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated SysMessageBo bo, HttpServletResponse response) {
        List<SysMessageVo> list = iSysMessageService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息通知", SysMessageVo.class, response);
    }

    /**
     * 获取消息通知详细信息
     */
    // @SaCheckPermission("workflow:message:query")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(iSysMessageService.queryById(id));
    }

    /**
     * 新增消息通知
     */
    // @SaCheckPermission("workflow:message:add")
    @Log(title = "消息通知", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody SysMessageBo bo) {
        return toAjax(iSysMessageService.sendMessage(bo) ? 1 : 0);
    }

    /**
     * 修改消息通知
     */
    // @SaCheckPermission("workflow:message:edit")
    @Log(title = "消息通知", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody SysMessageBo bo) {
        return toAjax(iSysMessageService.updateMessage(bo) ? 1 : 0);
    }

    /**
     * 删除消息通知
     */
    // @SaCheckPermission("workflow:message:remove")
    @Log(title = "消息通知", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(iSysMessageService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    /**
     * @Description: 阅读消息
     * @param: id
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/6/19 17:08
     */
    @Log(title = "消息通知", businessType = BusinessType.INSERT)
    @GetMapping("/readMessage/{id}")
    public AjaxResult readMessage(@PathVariable Long id) {
        return toAjax(iSysMessageService.readMessage(id) ? 1 : 0);
    }

    /**
     * @Description: 批量阅读消息
     * @return: com.ruoyi.common.core.domain.R<java.lang.Void>
     * @author: gssong
     * @Date: 2022/6/19 17:08
     */
    @Log(title = "消息通知", businessType = BusinessType.INSERT)
    @GetMapping("/batchReadMessage")
    public AjaxResult batchReadMessage() {
        return toAjax(iSysMessageService.batchReadMessage() ? 1 : 0);
    }
}
