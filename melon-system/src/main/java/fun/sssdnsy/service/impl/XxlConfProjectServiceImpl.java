package fun.sssdnsy.service.impl;

import fun.sssdnsy.domain.XxlConfProject;
import fun.sssdnsy.mapper.XxlConfProjectDao;
import fun.sssdnsy.service.IXxlConfProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.service.impl.XxlConfProjectServiceImpl
 * @desc
 * @since 2023-01-27
 */
@Service
public class XxlConfProjectServiceImpl implements IXxlConfProjectService {

    @Autowired
    private XxlConfProjectDao projectDao;

    @Override
    public List<XxlConfProject> selectConfigList(XxlConfProject project) {
        return projectDao.list(project);
    }

    @Override
    public String selectConfigByName(String appName) {
        return null;
    }

    @Override
    public boolean checkConfigKeyUnique(XxlConfProject project) {
        return false;
    }

    @Override
    public int insertConfig(XxlConfProject project) {
        return 0;
    }

    @Override
    public int updateConfig(XxlConfProject config) {
        return 0;
    }

    @Override
    public void deleteConfigByName(Long[] configIds) {

    }

}
