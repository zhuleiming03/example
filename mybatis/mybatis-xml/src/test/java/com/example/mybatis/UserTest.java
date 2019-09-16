package com.example.mybatis;

import com.example.mybatis.dao.UserDao;
import com.example.mybatis.enums.UserSexEnum;
import com.example.mybatis.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserDao dao;

    @Test
    public void selectAllTest() {
        List<UserPO> userPOList = dao.getUsers();
        for (UserPO po : userPOList) {
            System.out.println(">>po:" + po);
        }
    }

    @Test
    public void selectOneTest() {
        UserPO userPO = dao.getUser(10001L);
        System.out.println(">>po:" + userPO);
    }

    @Test
    public void insertTest() {
        UserPO po = new UserPO();
        po.setUserName("旗木卡卡西");
        po.setUserSex(UserSexEnum.MALE);
        po.setPassWord("f1d56a");
        po.setValid(Boolean.TRUE);
        Integer result = dao.save(po);
        if (result != null && result > 0) {
            System.out.println(">>新增 po:" + po);
        } else {
            System.out.println(">>保存失败");
        }
    }

    @Test
    public void deletTest() {

        UserPO po = new UserPO();
        po.setUserName("纲手");
        po.setUserSex(UserSexEnum.FEMALE);
        po.setPassWord("321dsa");
        po.setValid(Boolean.TRUE);
        Integer result = dao.save(po);
        if (result != null && result > 0) {
            System.out.println(">>新增 po:" + po);
        } else {
            System.out.println(">>保存失败");
        }

        result = dao.delete(po.getId());
        if (result != null && result > 0) {
            System.out.println(">>删除条数:" + result);
        } else {
            System.out.println(">>删除失败");
        }
    }

    @Test
    public void updateTest() {
        UserPO userPO = dao.getUser(10001L);
        System.out.println(">>修改前 po:" + userPO);
        userPO.setPassWord("4616fdaa");
        Integer result = dao.update(userPO);
        if (result != null && result > 0) {
            System.out.println(">>修改后 po:" + userPO);
        } else {
            System.out.println(">>修改失败");
        }
    }
}
