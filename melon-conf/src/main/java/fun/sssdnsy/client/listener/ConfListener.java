package fun.sssdnsy.client.listener;

/**
 * @Desc Conf listener
 * @Author pengzh
 * @Since 2023-03-24
 */
public interface ConfListener {

    /**
     * invoke when first-use or conf-change
     *
     * @param key
     */
    public void onChange(String key, String value) throws Exception;


}
