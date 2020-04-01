package example.spring.boot.annotation.enable.annotation;

import example.spring.boot.annotation.enable.service.impl.OnSystemPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    /**
     * @return System 属性名称
     */
    String name();

    /**
     * @return System 属性值
     */
    String value();
}
