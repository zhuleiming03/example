package com.example.config.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/serviceconfig.yml")
@ConfigurationProperties(prefix = "serviceconfig")
@Data
public class ServiceConfig {

    private String serverRootUrl;

    private String version;

}