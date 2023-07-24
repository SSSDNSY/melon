package fun.sssdnsy.translation.impl;

import fun.sssdnsy.annotation.TranslationType;
import fun.sssdnsy.constant.TransConstant;
import fun.sssdnsy.core.service.DictService;
import fun.sssdnsy.translation.TranslationInterface;
import fun.sssdnsy.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 字典翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTypeTranslationImpl implements TranslationInterface<String> {

    private final DictService dictService;

    public String translation(Object key, String other) {
        if (key instanceof String && StringUtils.isNotBlank(other)) {
            return dictService.getDictLabel(other, key.toString());
        }
        return null;
    }
}
