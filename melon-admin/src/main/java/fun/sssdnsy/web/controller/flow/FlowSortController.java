package fun.sssdnsy.web.controller.flow;

import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.FlowSort;
import fun.sssdnsy.service.IFlowSortService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Desc 流程分类控制器
 * @Author pengzh
 * @Since 2023-07-13
 */
@RestController
@RequestMapping("/flowSort")
public class FlowSortController extends BaseController {

    @Resource
    private IFlowSortService flowSortService;

    /**
     * 获取流程分类
     */
    @GetMapping("/list")
    public TableDataInfo list(FlowSort flowSort) {
        startPage();
        List<FlowSort> list = flowSortService.list(flowSort);
        return getDataTable(list);
    }

    /**
     * 获取全部流程分类
     */
    @GetMapping("/getAll")
    public AjaxResult getAll() {
        return AjaxResult.success(flowSortService.getAll());
    }

    /**
     * 保存流程分类
     */
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowSort flowSort) {
        return AjaxResult.success(flowSortService.save(flowSort));
    }

    /**
     * 获取流程分类树型结构数据
     *
     * @return
     */
    @GetMapping("/getTree")
    public AjaxResult getTree() {
        return AjaxResult.success(flowSortService.getTree());
    }

    /**
     * 删除流程分类
     */
    @DeleteMapping(value = "/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return AjaxResult.success(flowSortService.remove(ids));
    }

}
