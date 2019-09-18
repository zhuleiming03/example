package com.example.frame.dao;

import com.example.frame.dao.mapper.UserMapper;
import com.example.frame.domain.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("Test");
    }

    @Test
    public void selectOneTest() {
        UserPO userPO = mapper.selectById(10001L);
        System.out.println(">>po:" + userPO);
    }

    @Autowired
    private UserMapper mapper;
}
