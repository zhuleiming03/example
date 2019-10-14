package com.example.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConifgBaseTest {

    @Value("${server.port}")
    private Integer port;

    /**
     * 测试环境下查看端口需要加 (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
     */
    @Test
    public void configString() {
        System.out.println(">>默认字符串配置 port:" + port);
    }
}
