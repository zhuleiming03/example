package example.spring.boot.annotationaliasfor.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
public @interface DemoTwo {

    @AliasFor(value = "name", annotation = RequestMapping.class)
    String param() default "";

    String[] params() default {"test"};
}
