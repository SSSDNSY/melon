package fun.sssdnsy.service;


import fun.sssdnsy.domain.XxlConfEnv;
import fun.sssdnsy.domain.XxlConfEnv;

import java.util.List;


/**
 * @author xuxueli 2015-9-4 18:19:52
 */
public interface IXxlConfEnvService {

    List<XxlConfEnv> selectConfigList(XxlConfEnv env);

    XxlConfEnv selectByName(String appName);

    boolean checkConfigKeyUnique(XxlConfEnv env);

    int insertConfig(XxlConfEnv env);

    int updateConfig(XxlConfEnv config);

    void deleteConfigByName(String[] configIds);
}
