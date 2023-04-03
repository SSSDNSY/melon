package fun.sssdnsy.client.core;

import fun.sssdnsy.client.exception.ConfException;
import fun.sssdnsy.client.model.ConfParamVO;
import fun.sssdnsy.client.util.BasicHttpUtil;
import fun.sssdnsy.client.util.json.BasicJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class RemoteConf {

    public static final String MONITOR_API = "/config/conf/monitor";
    public static final String FIND_API = "/config/conf/find";
    public static final String KEY_DATA = "data";

    private static Logger log = LoggerFactory.getLogger(RemoteConf.class);

    private static String adminAddress;

    private static String env;

    private static String accessToken;

    private static List<String> adminAddressList = null;

    public static void init(String adminAddress, String env, String accessToken) {
        if (adminAddress == null || adminAddress.trim().length() == 0) {
            throw new ConfException("Conf adminAddress can't be empty.");
        }
        if (env == null || env.trim().length() == 0) {
            throw new ConfException("Conf env can't be empty.");
        }

        RemoteConf.adminAddress = adminAddress;
        RemoteConf.env = env;
        RemoteConf.accessToken = accessToken;

        RemoteConf.adminAddressList = new ArrayList<>();
        if (adminAddress.contains(",")) {
            RemoteConf.adminAddressList.addAll(Arrays.asList(adminAddress.split(",")));
        } else {
            RemoteConf.adminAddressList.add(adminAddress);
        }
    }

    private static Map<String, Object> getAndValid(String url, String reqeustBody, int timeout) {
        String respJosn = BasicHttpUtil.postBody(url, reqeustBody, timeout);
        if (respJosn == null) {
            return null;
        }
        Map<String, Object> respMap = BasicJson.parseMap(respJosn);
        int code = Integer.valueOf(String.valueOf(respMap.get("code")));
        if (code != 200) {
            log.info("request fail, msg={}", (respMap.containsKey("msg") ? respMap.get("msg") : respJosn));
            return null;
        }
        return respMap;
    }


    /**
     * find
     *
     * @param keys
     * @return
     */
    public static Map<String, String> find(Set<String> keys) {
        for (String adminAddressUrl : RemoteConf.adminAddressList) {

            // url + param
            String url = adminAddressUrl + FIND_API;

            ConfParamVO paramVO = new ConfParamVO();
            paramVO.setAccessToken(accessToken);
            paramVO.setEnv(env);
            paramVO.setKeys(new ArrayList<String>(keys));

            String paramsJson = BasicJson.toJson(paramVO);

            // get and valid
            Map<String, Object> respObj = getAndValid(url, paramsJson, 5);

            // parse
            if (respObj != null && respObj.containsKey(KEY_DATA)) {
                Map<String, String> data = (Map<String, String>) respObj.get(KEY_DATA);
                return data;
            }
        }

        return null;
    }

    public static String find(String key) {
        Map<String, String> result = find(new HashSet<String>(Arrays.asList(key)));
        if (result != null) {
            return result.get(key);
        }
        return null;
    }


    /**
     * monitor
     *
     * @param keys
     * @return
     */
    public static boolean monitor(Set<String> keys) {

        for (String adminAddressUrl : RemoteConf.adminAddressList) {

            // url + param
            String url = adminAddressUrl + MONITOR_API;

            ConfParamVO paramVO = new ConfParamVO();
            paramVO.setAccessToken(accessToken);
            paramVO.setEnv(env);
            paramVO.setKeys(new ArrayList<String>(keys));

            String paramsJson = BasicJson.toJson(paramVO);

            // get and valid
            Map<String, Object> respObj = getAndValid(url, paramsJson, 60);

            return respObj != null ? true : false;
        }
        return false;
    }

}
