package fun.sssdnsy.service;


import fun.sssdnsy.domain.XxlConfNode;

import java.util.List;
import java.util.Map;


/**
 * @author xuxueli 2015-9-4 18:19:52
 */
public interface IXxlConfNodeService {


    List<XxlConfNode> find(List<XxlConfNode> confNodeList);

    XxlConfNode get(XxlConfNode confNode);

    List<XxlConfNode> list(XxlConfNode confNode);

    boolean exist(XxlConfNode confNode);

    int delete(XxlConfNode confNode);

    int delete(List<XxlConfNode> confNodeList);

    int add(XxlConfNode xxlConfNode);

    int update(XxlConfNode xxlConfNode);

    void monitor(List<XxlConfNode> confNodeList);

    Map<String, List> listEnvApp();

}
