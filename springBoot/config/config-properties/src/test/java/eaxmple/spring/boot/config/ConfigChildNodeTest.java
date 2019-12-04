package eaxmple.spring.boot.config;

import eaxmple.spring.boot.config.domain.ServiceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigChildNodeTest {

    @Autowired
    private ServiceConfig serviceConfig;

    @Test
    void configModel() {
        System.out.println(">>自定义对象字符串配置 serverRootUrl:" + serviceConfig.getServerRootUrl());
        System.out.println(">>自定义对象字符串配置 version:" + serviceConfig.getVersion());
    }
}
