package fun.sssdnsy.translation.impl;


import fun.sssdnsy.annotation.TranslationType;
import fun.sssdnsy.constant.TransConstant;
import fun.sssdnsy.core.service.OssService;
import fun.sssdnsy.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * OSS翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.OSS_ID_TO_URL)
public class OssUrlTranslationImpl implements TranslationInterface<String> {

    private final OssService ossService;

    public String translation(Object key, String other) {
        return ossService.selectUrlByIds(key.toString());
    }
}
