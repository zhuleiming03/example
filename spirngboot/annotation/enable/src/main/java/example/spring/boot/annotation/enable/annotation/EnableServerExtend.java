package example.spring.boot.annotation.enable.annotation;

import example.spring.boot.annotation.enable.service.impl.ServerImportBeanDefinitionRegistrar;
import example.spring.boot.annotation.enable.service.Server;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableServerExtend {

    /**
     * 设置服务类型
     *
     * @return
     */
    Server.Type type();
}
