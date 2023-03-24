package fun.sssdnsy.client.factory;

import fun.sssdnsy.client.core.LocalCacheConf;
import fun.sssdnsy.client.core.MirrorConf;
import fun.sssdnsy.client.core.RemoteConf;
import fun.sssdnsy.client.listener.ConfListenerFactory;
import fun.sssdnsy.client.listener.impl.BeanRefreshConfListener;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class ConfBaseFactory {

    public static void init(String adminAddress, String env, String accessToken, String mirrorFile) {
        RemoteConf.init(adminAddress, env, accessToken);
        MirrorConf.init(mirrorFile);
        LocalCacheConf.init();
        ConfListenerFactory.addListener(null, new BeanRefreshConfListener());
    }

    public static void destroy() {
        LocalCacheConf.destroy();
    }

}
