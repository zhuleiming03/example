package com.example.java;

import com.example.java.implement.CacheServiceImpl;
import com.example.java.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TryTest {

    /**
     * finally 里不能存在 Return
     */
    @Test
    public void finallyReturnTest() {
        CacheService service = new CacheServiceImpl();
        Integer result = service.getTryCatchException(10);
        System.out.println("入参：10 结果:" + result);

        result = service.getTryCatchException(null);
        System.out.println("入参：null 结果:" + result);
    }
}
