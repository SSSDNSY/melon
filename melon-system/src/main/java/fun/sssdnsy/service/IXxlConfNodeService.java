package fun.sssdnsy.service;


import fun.sssdnsy.domain.XxlConfNode;
import fun.sssdnsy.domain.XxlConfNode;

import java.util.List;
import java.util.Map;


/**
 * @author xuxueli 2015-9-4 18:19:52
 */
public interface IXxlConfNodeService {

    boolean ifHasProjectPermission(String loginEnv, String appname);

    Map<String, Object> pageList(int offset,
                                 int pagesize,
                                 String appname,
                                 String key,

                                 String loginEnv);

    String delete(String key, String loginEnv);

    String add(XxlConfNode xxlConfNode, String loginEnv);

    String update(XxlConfNode xxlConfNode, String loginEnv);

    /*ReturnT<String syncConf(String appname,  String loginEnv);*/


    // ---------------------- rest api ----------------------

    Map<String, String> find(String accessToken, String env, List<String> keys);

    String monitor(String accessToken, String env, List<String> keys);


    List<XxlConfNode> selectConfigList(XxlConfNode confNode);

    XxlConfNode selectConfigByName(String appName);

    boolean checkConfigKeyUnique(XxlConfNode confNode);

    int insertConfig(XxlConfNode confNode);

    int updateConfig(XxlConfNode config);

    void deleteConfigByName(String[] configIds);

}
