package fun.sssdnsy.client.listener;

import fun.sssdnsy.client.ConfClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class ConfListenerFactory {

    private static Logger log = LoggerFactory.getLogger(ConfListenerFactory.class);

    private static ConcurrentHashMap<String, List<ConfListener>> keyListenerRepository = new ConcurrentHashMap<>();

    private static List<ConfListener> noKeyConfListener = Collections.synchronizedList(new ArrayList<ConfListener>());

    public static boolean addListener(String key, ConfListener confListener) {
        if (confListener == null) {
            return false;
        }

        if (key == null || key.trim().length() == 0) {
            noKeyConfListener.add(confListener);
        } else {
            try {
                String value = ConfClient.get(key);
                confListener.onChange(key, value);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            List<ConfListener> listeners = keyListenerRepository.get(key);
            if (listeners == null) {
                listeners = new ArrayList<>();
                keyListenerRepository.put(key, listeners);
            }
            listeners.add(confListener);
        }
        return true;
    }

    public static void onChange(String key, String value) {
        if (key == null || key.trim().length() == 0) {
            return;
        }
        List<ConfListener> listeners = keyListenerRepository.get(key);
        if (listeners != null && listeners.size() > 0) {
            for (ConfListener listener : listeners) {
                try {
                    listener.onChange(key, value);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        if (noKeyConfListener.size() > 0) {
            for (ConfListener listener : noKeyConfListener) {
                try {
                    listener.onChange(key, value);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}
