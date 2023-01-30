package fun.sssdnsy.service.impl;

import fun.sssdnsy.domain.XxlConfProject;
import fun.sssdnsy.mapper.XxlConfProjectDao;
import fun.sssdnsy.service.IXxlConfProjectService;
import org.apache.commons.lang3.StringUtils;
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
    public XxlConfProject selectConfigByName(String appName) {
        return projectDao.getOne(appName);
    }

    @Override
    public boolean checkConfigKeyUnique(XxlConfProject project) {
        XxlConfProject xxlConfProject = selectConfigByName(project.getAppname());
        return xxlConfProject != null && StringUtils.isNotBlank(xxlConfProject.getAppname());
    }

    @Override
    public int insertConfig(XxlConfProject project) {
        return projectDao.save(project);
    }

    @Override
    public int updateConfig(XxlConfProject project) {
        return projectDao.update(project);
    }

    @Override
    public void deleteConfigByName(String[] appNames) {
        projectDao.delete(appNames);
    }

}
