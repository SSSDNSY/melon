package fun.sssdnsy.service.impl;

import fun.sssdnsy.domain.XxlConfEnv;
import fun.sssdnsy.mapper.XxlConfEnvDao;
import fun.sssdnsy.service.IXxlConfEnvService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fun.sssdnsy
 * @class fun.sssdnsy.service.impl.XxlConfEnvServiceImpl
 * @desc
 * @since 2023-01-27
 */
@Service
public class XxlConfEnvServiceImpl implements IXxlConfEnvService {

    @Resource
    private XxlConfEnvDao envDao;

    @Override
    public List<XxlConfEnv> selectConfigList(XxlConfEnv env) {
        return envDao.list(env);
    }

    @Override
    public XxlConfEnv selectByName(String appName) {
        return envDao.getOne(appName);
    }

    @Override
    public boolean checkConfigKeyUnique(XxlConfEnv env) {
        XxlConfEnv xxlConfProject = selectByName(env.getEnv());
        return xxlConfProject != null && StringUtils.isNotBlank(xxlConfProject.getEnv());
    }

    @Override
    public int insertConfig(XxlConfEnv env) {
        return envDao.save(env);
    }

    @Override
    public int updateConfig(XxlConfEnv env) {
        return envDao.update(env);
    }

    @Override
    public void deleteConfigByName(String[] appNames) {
        envDao.delete(appNames);
    }

}
