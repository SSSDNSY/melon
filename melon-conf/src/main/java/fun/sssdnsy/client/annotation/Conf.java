package fun.sssdnsy.client.annotation;

import java.lang.annotation.*;

/**
 * @Desc Conf Annotation Only Support field
 * @Author pengzh
 * @Since 2023-03-24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Conf {

    String value();

    String defaultValue() default "";

    boolean callback() default true;
}
