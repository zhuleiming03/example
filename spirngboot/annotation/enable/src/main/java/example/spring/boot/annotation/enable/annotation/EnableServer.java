package example.spring.boot.annotation.enable.annotation;

import example.spring.boot.annotation.enable.service.impl.ServerImportSelector;
import example.spring.boot.annotation.enable.service.Server;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerImportSelector.class)
public @interface EnableServer {

    /**
     * 设置服务类型
     *
     * @return
     */
    Server.Type type();
}
