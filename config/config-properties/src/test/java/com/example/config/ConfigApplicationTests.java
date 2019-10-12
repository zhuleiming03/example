package com.example.config;

import com.example.config.domain.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConfigApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Value("${server.port}")
    private Integer port;

    /**
     * 测试环境下查看端口需要加 (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
     */
    @Test
    public void configString() {
        System.out.println(">>默认字符串配置 port:" + port);
    }

    @Autowired
    private ServiceConfig serviceConfig;

    @Test
    public void configModel(){
        System.out.println(">>自定义对象字符串配置 serverRootUrl:" + serviceConfig.serverRootUrl);
        System.out.println(">>自定义对象字符串配置 version:" + serviceConfig.version);
    }

}
