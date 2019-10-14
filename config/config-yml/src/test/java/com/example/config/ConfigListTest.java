package com.example.config;

import com.example.config.domain.NovelListConifg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigListTest {

    @Autowired
    NovelListConifg conifg;

    @Test
    public void NovelTest(){System.out.println(">>NovelList:" + conifg);}
}
