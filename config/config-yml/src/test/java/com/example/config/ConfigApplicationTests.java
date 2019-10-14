package com.example.config;

import com.example.config.domain.ServiceConfig;
import com.example.config.domain.po.PersonPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("Test");
    }

    @Autowired
    private PersonPO person;

    @Test
    public void configEntity(){
        System.out.println(">>Person:" + person);
    }
}
