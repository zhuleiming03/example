package com.example.config;

import com.example.config.domain.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigChildNodeTest {

    @Autowired
    private ServiceConfig serviceConfig;

    @Test
    public void configModel(){
        System.out.println(">>自定义对象字符串配置 serverRootUrl:" + serviceConfig.getServerRootUrl());
        System.out.println(">>自定义对象字符串配置 version:" + serviceConfig.getVersion());
    }
}
