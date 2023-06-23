package fun.sssdnsy.service;


import com.alibaba.fastjson2.JSONObject;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.domain.XxlConfNode;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;


/**
 * @author xuxueli 2015-9-4 18:19:52
 */
public interface IXxlConfNodeService {

    Map<String, String> find(JSONObject jsonObject);

    DeferredResult<AjaxResult> monitor(JSONObject jsonObject);


    XxlConfNode get(XxlConfNode confNode);

    List<XxlConfNode> list(XxlConfNode confNode);

    boolean exist(XxlConfNode confNode);

    int delete(XxlConfNode confNode);

    int delete(List<XxlConfNode> confNodeList);

    int add(XxlConfNode xxlConfNode);

    int update(XxlConfNode xxlConfNode);

    Map<String, List> listEnvApp();

}
