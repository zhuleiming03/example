package eaxmple.spring.boot.config.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/service.properties")
@Data
public class ServiceConfig {

    @Value("${serviceconfig.serverRootUrl}")
    private String serverRootUrl;

    @Value("${serviceconfig.version}")
    private Integer version;

}
