package example.spring.boot.annotation.enable.annotation;

import example.spring.boot.annotation.enable.instance.config.HelloWorldConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
