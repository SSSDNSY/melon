package fun.sssdnsy.client.core;

import fun.sssdnsy.client.listener.ConfListenerFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class LocalCacheConf {

    private static Logger log = LoggerFactory.getLogger(LocalCacheConf.class);

    private static ConcurrentHashMap<String, CacheNode> localCacheRepository = null;

    private static Thread refreshThread;

    private static boolean refreshThreadStop = false;

    public static void init() {
        localCacheRepository = new ConcurrentHashMap<String, CacheNode>();

        Map<String, String> preConfData = new HashMap();

        Map<String, String> mirrorConfData = MirrorConf.readConfMirror();

        Map<String, String> remoteConfData = null;
        if (mirrorConfData != null && mirrorConfData.size() > 0) {
            remoteConfData = RemoteConf.find(mirrorConfData.keySet());
            preConfData.putAll(mirrorConfData);
        }
        if (remoteConfData != null && remoteConfData.size() > 0) {
            preConfData.putAll(remoteConfData);
        }

        if (preConfData != null && preConfData.size() > 0) {
            for (String preKey : preConfData.keySet()) {
                set(preKey, preConfData.get(preKey), SetType.PRELOAD);
            }
        }

        refreshThread = new Thread(() -> {
            while (!refreshThreadStop) {
                try {
                    refreshCacheAndMirror();
                } catch (Exception e) {
                    if (!refreshThreadStop && (e instanceof InterruptedException)) {
                        log.error(">>>>>>>>>> Conf refresh thread error.");
                        log.error(e.getMessage(), e);
                    }
                }
            }
        });

        refreshThread.setDaemon(true);
        refreshThread.start();
        log.info(">>>>>>>>>> Conf localCacheConf init success.");
    }

    public static void destroy() {
        if (refreshThread != null) {
            refreshThreadStop = true;
            refreshThread.interrupt();
        }
    }


    private static void refreshCacheAndMirror() throws InterruptedException {
        if (localCacheRepository.size() == 0) {
            TimeUnit.SECONDS.sleep(3);
            return;
        }

        boolean monitroRet = RemoteConf.monitor(localCacheRepository.keySet());

        if (!monitroRet) {
            TimeUnit.SECONDS.sleep(10);
        }

        Set<String> keySet = localCacheRepository.keySet();

        if (keySet.size() > 0) {
            Map<String, String> remoteDataMap = RemoteConf.find(keySet);
            if (remoteDataMap != null && remoteDataMap.size() > 0) {
                for (String remoteKey : remoteDataMap.keySet()) {
                    String remoteData = remoteDataMap.get(remoteKey);
                    CacheNode existNode = localCacheRepository.get(remoteKey);
                    if (existNode != null && existNode.getValue() != null && existNode.getValue().equals(remoteData)) {
                        log.debug(">>>>>>>>>> Conf: reload unchanged pass [{}].", remoteKey);
                    } else {
                        set(remoteKey, remoteData, SetType.RELOAD);
                    }
                }
            }
        }
        Map<String, String> mirrorConfData = new HashMap();
        for (String key : keySet) {
            CacheNode existNode = localCacheRepository.get(key);
            mirrorConfData.put(key, existNode.getValue() != null ? existNode.getValue() : "");
        }
        MirrorConf.writeConfMirror(mirrorConfData);

        log.debug(">>>>>>>>>> Conf, refreshCacheAndMirror success.");
    }

    private static CacheNode get(String key) {
        if (localCacheRepository.containsKey(key)) {
            CacheNode node = localCacheRepository.get(key);
            return node;
        }
        return null;
    }

    private static void set(String key, String value, SetType type) {
        localCacheRepository.put(key, new CacheNode(value));
        log.info(">>>>>>>>>> Conf,{}: [{}={}]", type, key, value);
        if (type == SetType.RELOAD) {
            ConfListenerFactory.onChange(key, value);
        }

        if (type == SetType.SET) {
            refreshThread.interrupt();
        }
    }

    public static String get(String key, String defaultValue) {
        CacheNode cacheNode = get(key);
        if (cacheNode != null) {
            return cacheNode.getValue();
        }

        String remoteData = null;
        try {
            remoteData = RemoteConf.find(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        set(key, remoteData, SetType.SET);
        if (remoteData != null) {
            return remoteData;
        }
        return defaultValue;
    }

    public enum SetType {
        SET,//first use
        RELOAD,// value updated
        PRELOAD// pre hot
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CacheNode implements Serializable {
        private static final long serialVersionUID = 112341234243L;
        private String value;
    }

}
