package fun.sssdnsy.client.core;

import fun.sssdnsy.client.exception.ConfException;
import fun.sssdnsy.client.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class MirrorConf {

    private static Logger log = LoggerFactory.getLogger(MirrorConf.class);

    private static String mirrorFile = null;

    public static void init(String mirrorFileParam) {
        if (mirrorFileParam == null || mirrorFileParam.trim().length() == 0) {
            throw new ConfException("Conf mirrorFileParam can't be empty");
        }
        mirrorFile = mirrorFileParam;
    }

    public static Map<String, String> readConfMirror() {
        Properties mirrorProp = PropUtil.loadFileProp(mirrorFile);
        if (mirrorProp != null && mirrorProp.stringPropertyNames() != null && mirrorProp.stringPropertyNames().size() > 0) {
            Map<String, String> mirrorConfData = new HashMap<>();
            for (String key : mirrorProp.stringPropertyNames()) {
                mirrorConfData.put(key, mirrorProp.getProperty(key));
            }
            return mirrorConfData;
        }
        return null;
    }

    public static void writeConfMirror(Map<String, String> mirrorConfDataParam) {
        Properties properties = new Properties();
        for (Map.Entry<String, String> conf : mirrorConfDataParam.entrySet()) {
            properties.setProperty(conf.getKey(), conf.getValue());
        }
        PropUtil.writeFileProp(properties, mirrorFile);
    }

}
