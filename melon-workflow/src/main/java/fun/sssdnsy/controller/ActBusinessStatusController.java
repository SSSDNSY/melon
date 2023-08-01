package fun.sssdnsy.controller;

import fun.sssdnsy.domain.ActBusinessStatus;
import fun.sssdnsy.core.domain.AjaxResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fun.sssdnsy.service.IActBusinessStatusService;

import javax.validation.constraints.NotBlank;

/**
 * @program: ruoyi-vue-plus
 * @description: 流程与业务关联控制器
 * @author: gssong
 * @created: 2021/10/16 16:18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/actBusiness")
public class ActBusinessStatusController {

    private final IActBusinessStatusService iActBusinessStatusService;

    /**
     * @Description: 根据业务id查询流程实例
     */
    @GetMapping("/getInfoByBusinessKey/{businessKey}")
    public AjaxResult getInfoByBusinessId(@NotBlank(message = "业务id不能为空") @PathVariable String businessKey) {
        ActBusinessStatus actBusinessStatus = iActBusinessStatusService.getInfoByBusinessKey(businessKey);
        return AjaxResult.success(actBusinessStatus);
    }

}
