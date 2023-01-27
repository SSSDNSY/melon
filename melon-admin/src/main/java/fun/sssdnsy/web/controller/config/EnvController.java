package fun.sssdnsy.web.controller.config;

import fun.sssdnsy.core.controller.BaseController;
import fun.sssdnsy.domain.XxlConfEnv;
import fun.sssdnsy.mapper.XxlConfEnvDao;
import fun.sssdnsy.mapper.XxlConfNodeDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 环境管理
 *
 * @author xuxueli 2018-05-30
 */
@RestController
@RequestMapping("/config/env")
public class EnvController extends BaseController {

}
