package example.spring.boot.annotation.metadata.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController(value = "rest")
public @interface Demo {

    String name() default "";

    String value() default "";
}
