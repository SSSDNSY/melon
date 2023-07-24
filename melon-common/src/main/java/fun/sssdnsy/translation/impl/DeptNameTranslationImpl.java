package fun.sssdnsy.translation.impl;

import fun.sssdnsy.annotation.TranslationType;
import fun.sssdnsy.constant.TransConstant;
import fun.sssdnsy.core.service.DeptService;
import fun.sssdnsy.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DEPT_ID_TO_NAME)
public class DeptNameTranslationImpl implements TranslationInterface<String> {

    private final DeptService deptService;

    public String translation(Object key, String other) {
        return deptService.selectDeptNameByIds(key.toString());
    }
}
