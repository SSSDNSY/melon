package fun.sssdnsy.client.spring;

import fun.sssdnsy.client.ConfClient;
import fun.sssdnsy.client.annotation.Conf;
import fun.sssdnsy.client.exception.ConfException;
import fun.sssdnsy.client.factory.ConfBaseFactory;
import fun.sssdnsy.client.listener.impl.BeanRefreshConfListener;
import fun.sssdnsy.client.util.FieldReflectionUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.*;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * @Desc configuration factory
 * @Author pengzh
 * @Since 2023-03-24
 */
@Getter
@Setter
@ToString
public class ConfigFactory implements InstantiationAwareBeanPostProcessor, InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware {

    private static final String placeHolderPrefix = "$Conf{";
    private static final String placeHolderSuffix = "}";
    private static Logger log = LoggerFactory.getLogger(ConfigFactory.class);
    private static BeanFactory beanFactory;
    private String adminAddress;
    private String env;
    private String accessToken;
    private String mirrorFile;
    private String beanName;

    public static void refreshBeanField(final BeanRefreshConfListener.BeanField beanField, final String value, Object bean) {
        if (bean == null) {
            bean = ConfigFactory.beanFactory.getBean(beanField.getBeanName());
        }
        if (bean == null) {
            return;
        }

        BeanWrapper beanWrapper = new BeanWrapperImpl(bean);

        PropertyDescriptor propertyDescriptor = null;
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        if (propertyDescriptors != null && propertyDescriptors.length > 0) {
            for (PropertyDescriptor item : propertyDescriptors) {
                if (beanField.getProperty().equals(item.getName())) {
                    propertyDescriptor = item;
                }
            }
        }

        if (propertyDescriptor != null && propertyDescriptor.getWriteMethod() != null) {
            beanWrapper.setPropertyValue(beanField.getProperty(), value);
            log.info(">>>>>>>>>>> Conf refreshBeanField[set] success, {}#{}:{}", beanField.getBeanName(), beanField.getProperty(), value);
        } else {
            final Object finalBean = bean;
            ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    if (beanField.getProperty().equals(field.getName())) {
                        try {
                            Object valueObje = FieldReflectionUtil.parseValue(field.getType(), value);
                            field.setAccessible(true);
                            field.set(finalBean, valueObje);
                            log.info(">>>>>>>>>>> Conf refreshBeanField[filed] success, {}#{}:{}", beanField.getBeanName(), beanField.getProperty(), value);
                        } catch (IllegalAccessException e) {
                            throw new ConfException(e);
                        }
                    }
                }
            });
        }
    }

    private static boolean xmlKeyValid(String originKey) {
        boolean start = originKey.startsWith(placeHolderPrefix);
        boolean end = originKey.endsWith(placeHolderSuffix);
        return start && end;
    }

    private static String xmlKeyParse(String originKey) {
        if (xmlKeyValid(originKey)) {
            String key = originKey.substring(placeHolderPrefix.length(), originKey.length() - placeHolderSuffix.length());
            return key;
        }
        return null;
    }

    /**
     * Annotation (@Conf): resolve conf + watch
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (!beanName.equals(this.beanName)) {
            ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    if (field.isAnnotationPresent(Conf.class)) {
                        String filedName = field.getName();
                        Conf conf = field.getAnnotation(Conf.class);

                        String confKey = conf.value();
                        String confValue = ConfClient.get(confKey, conf.defaultValue());

                        //resolve palaceHolders
                        BeanRefreshConfListener.BeanField beanField = new BeanRefreshConfListener.BeanField(beanName, filedName);
                        refreshBeanField(beanField, confValue, bean);

                        if (conf.callback()) {
                            // watch
                            BeanRefreshConfListener.addBeanField(confKey, beanField);
                        }
                    }
                }
            });
        }
        return true;
    }


    /**
     * XML('$XxlConf{...}')：resolves placeholders + watch
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

        if (!beanName.equals(this.beanName)) {
            PropertyValue[] pvArr = pvs.getPropertyValues();
            for (PropertyValue pv : pvArr) {
                if (pv.getValue() instanceof TypedStringValue) {
                    String propertyName = pv.getName();
                    String typeStringVal = ((TypedStringValue) pv.getValue()).getValue();
                    if (xmlKeyValid(typeStringVal)) {
                        String confKey = xmlKeyParse(typeStringVal);
                        String confValue = ConfClient.get(confKey, "");

                        BeanRefreshConfListener.BeanField beanField = new BeanRefreshConfListener.BeanField(beanName, propertyName);

                        Class propClass = String.class;
                        for (PropertyDescriptor item : pds) {
                            if (beanField.getProperty().equals(item.getName())) {
                                propClass = item.getPropertyType();
                            }
                        }
                        Object valueObj = FieldReflectionUtil.parseValue(propClass, confValue);
                        pv.setConvertedValue(valueObj);

                        // watch
                        BeanRefreshConfListener.addBeanField(confKey, beanField);
                    }
                }
            }
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = beanName;
    }

    @Override
    public void destroy() throws Exception {
        ConfBaseFactory.destroy();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ConfBaseFactory.init(adminAddress, env, accessToken, mirrorFile);
    }

}
