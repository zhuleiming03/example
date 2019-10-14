package com.example.config.domain;

import com.example.config.domain.po.TrafficPO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "person")
public class PersonConfig {

    private String lastName;

    private Integer age;

    private Boolean boss;

    private Date birth;

    private Map<String, Object> payment;

    private Map<String, String> pet;

    private List<String> address;

    private List<String> phones;

    private TrafficPO traffic;
}
