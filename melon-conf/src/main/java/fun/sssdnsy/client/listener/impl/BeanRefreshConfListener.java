package fun.sssdnsy.client.listener.impl;

import fun.sssdnsy.client.listener.ConfListener;
import fun.sssdnsy.client.spring.ConfigFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class BeanRefreshConfListener implements ConfListener {


    private static Map<String, List<BeanField>> key2BeanField = new ConcurrentHashMap<>();

    public static void addBeanField(String key, BeanField beanField) {
        List<BeanField> beanFieldList = key2BeanField.get(key);
        if (beanFieldList == null) {
            beanFieldList = new ArrayList<>();
            key2BeanField.put(key, beanFieldList);
        }
        for (BeanField field : beanFieldList) {
            if (field.getBeanName().equals(beanField.getBeanName()) && field.getProperty().equals(beanField.getProperty())) {
                return;
            }
        }
        beanFieldList.add(beanField);
    }

    @Override
    public void onChange(String key, String value) throws Exception {
        List<BeanField> beanFieldList = key2BeanField.get(key);
        if (beanFieldList != null && beanFieldList.size() > 0) {
            for (BeanField field : beanFieldList) {
                ConfigFactory.refreshBeanField(new BeanField(), value, null);
            }
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BeanField {
        private String beanName;
        private String property;
    }

}
