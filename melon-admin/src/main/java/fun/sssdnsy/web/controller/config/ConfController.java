package fun.sssdnsy.web.controller.config;

import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.core.page.TableDataInfo;
import fun.sssdnsy.domain.XxlConfNode;
import fun.sssdnsy.domain.XxlConfProject;
import fun.sssdnsy.mapper.XxlConfProjectDao;
import fun.sssdnsy.service.IXxlConfNodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 配置管理
 *
 * @author xuxueli
 */
@RestController
@RequestMapping("/config/conf")
public class ConfController extends BaseController {


}
