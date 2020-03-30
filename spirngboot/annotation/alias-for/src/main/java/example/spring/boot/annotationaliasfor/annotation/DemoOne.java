package example.spring.boot.annotationaliasfor.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DemoOne {

    @AliasFor("param")
    String name() default "";

    @AliasFor("name")
    String param() default "";

}
