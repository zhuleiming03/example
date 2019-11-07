package com.example.java.reflect;

import com.example.java.reflect.instance.ReflectBook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReflectBookTest {

    @Test
    void contextLoads() {

        try {

            // 创建对象
            ReflectBook.reflectNewInstance();

            // 反射私有的构造方法
            ReflectBook.reflectPrivateConstructor();

            // 反射私有属性
            ReflectBook.reflectPrivateField();

            // 反射私有方法
            ReflectBook.reflectPrivateMethod();

        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
