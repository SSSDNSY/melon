package fun.sssdnsy.client.util.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class BasicJson {
    private static final BasicJsonReader basicJsonReader = new BasicJsonReader();
    private static final BasicJsonWriter basicJsonWriter = new BasicJsonWriter();

    /**
     * object to json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return basicJsonWriter.toJson(object);
    }

    /**
     * json to List<Object>
     *
     * @param json
     * @return
     */
    public static List<Object> parseList(String json) {
        return basicJsonReader.parseList(json);
    }

    /**
     * json to Map<String, Object>
     *
     * @param json
     * @return
     */
    public static Map<String, Object> parseMap(String json) {
        return basicJsonReader.parseMap(json);
    }

    public static void main(String[] args) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("arr", Arrays.asList("111", "222"));
        result.put("float", 1.11f);

        String json = toJson(result);
        System.out.println(json);

        Object jsonObj2 = parseMap(json);
        System.out.println(jsonObj2);
    }


}
