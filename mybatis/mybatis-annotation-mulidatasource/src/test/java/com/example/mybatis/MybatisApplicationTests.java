package com.example.mybatis;

import com.example.mybatis.dao.UserDao;
import com.example.mybatis.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("Test");
    }

    @Test
    public void getAllUserTest() {
        List<UserPO> poList = dao.getUsers();
        for (UserPO po : poList) {
            System.out.println(">>po:" + po);
        }
    }

    @Test
    public void getUserByNameTest() {
        UserPO po = dao.getUser("亚丝娜");
        System.out.println(">>po:" + po);
    }

    @Autowired
    private UserDao dao;

}
