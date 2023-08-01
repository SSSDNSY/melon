package fun.sssdnsy.controller;

import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.domain.bo.SysUserBo;
import fun.sssdnsy.domain.bo.SysUserMultiBo;
import fun.sssdnsy.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: 工作流用户信息控制器
 * @author: gssong
 * @created: 2022-03-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/user")
public class UserController {

    private final IUserService iUserService;

    /**
     * @Description: 分页查询工作流选人, 角色，部门等
     */
    @PostMapping("/getWorkflowUserListByPage")
    public AjaxResult getWorkflowUserListByPage(@RequestBody SysUserBo sysUserBo) {
        Map<String, Object> map = iUserService.getWorkflowUserListByPage(sysUserBo);
        return AjaxResult.success(map);
    }

    /**
     * @Description: 分页查询工作流选择加签人员
     */
    @PostMapping("/getWorkflowAddMultiListByPage")
    public AjaxResult getWorkflowAddMultiListByPage(@RequestBody SysUserMultiBo sysUserMultiBo) {
        Map<String, Object> map = iUserService.getWorkflowAddMultiListByPage(sysUserMultiBo);
        return AjaxResult.success(map);
    }
}
