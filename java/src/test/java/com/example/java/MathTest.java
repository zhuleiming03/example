package com.example.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathTest {

    /**
     * 获取随机数
     */
    @Test
    public void randomTest() {

        double randomDouble = Math.random();
        System.out.println("获取一个双精度随机数：" + randomDouble);

        int randomInt = new Random().nextInt();
        System.out.println("获取一个整数随机数：" + randomInt);

        long randomLong = new Random().nextLong();
        System.out.println("获取一个长整数随机数：" + randomLong);
    }
}
