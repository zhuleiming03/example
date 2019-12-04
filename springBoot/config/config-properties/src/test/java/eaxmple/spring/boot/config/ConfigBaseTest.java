package eaxmple.spring.boot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class ConfigBaseTest {

    @Value("${user}")
    private String name;

    @Test
    void configString() {
        System.out.println(">>默认字符串配置 user:" + name);
    }
}
