package com.example.config.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/serviceconfig.properties")
@Data
public class ServiceConfig {

    @Value("${serviceconfig.serverRootUrl}")
    public String serverRootUrl;

    @Value("${serviceconfig.version}")
    public String version;

}
