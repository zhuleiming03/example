package com.example.config;

import com.example.config.domain.PersonConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigModelTest {

    @Autowired
    private PersonConfig person;

    @Test
    public void configModel(){
        System.out.println(">>Person:" + person);
    }
}
