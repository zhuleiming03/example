package com.example.java.reflect;

import com.example.java.reflect.instance.ReflectBaseAndSub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReflectBaseAndSubTest {

    @Autowired
    private ReflectBaseAndSub baseAndSub;

    @Test
    void doWorkTest() {
        System.out.println(baseAndSub.doWorkTest("com.example.java.reflect.domain.Base"));
        System.out.println(baseAndSub.doWorkTest("com.example.java.reflect.domain.SubOne"));
        System.out.println(baseAndSub.doWorkTest("com.example.java.reflect.domain.SubTwo"));
    }

    @Test
    void SubTwoTest(){
        System.out.println(baseAndSub.subTwoTest("getFirstMethod", "ABC"));
        System.out.println(baseAndSub.subTwoTest("getSecondMethod", "Hello"));
    }

}
