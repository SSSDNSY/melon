package fun.sssdnsy.service;


import fun.sssdnsy.domain.XxlConfProject;

import java.util.List;


/**
 * @author xuxueli 2015-9-4 18:19:52
 */
public interface IXxlConfProjectService {


    List<XxlConfProject> selectConfigList(XxlConfProject project);

    XxlConfProject selectConfigByName(String appName);

    boolean checkConfigKeyUnique(XxlConfProject project);

    int insertConfig(XxlConfProject project);

    int updateConfig(XxlConfProject config);

    void deleteConfigByName(String[] configIds);
}
