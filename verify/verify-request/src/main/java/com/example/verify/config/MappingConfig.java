package com.example.verify.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "mapping-items")
public class MappingConfig {

    private final List<MappingModel> MappingItems = new ArrayList<>();

    @Data
    public static class MappingModel {

        private String source;

        private String target;

        private String defaultValue;

        private boolean required;

    }
}
