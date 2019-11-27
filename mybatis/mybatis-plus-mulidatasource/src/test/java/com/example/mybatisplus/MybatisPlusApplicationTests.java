package com.example.mybatisplus;


import com.example.mybatisplus.mapper.dev.EnumTableMapper;
import com.example.mybatisplus.mapper.prod.SubjectMapper;
import com.example.mybatisplus.mapper.mysql.UserMapper;
import com.example.mybatisplus.po.dev.EnumTablePO;
import com.example.mybatisplus.po.mysql.UserPO;
import com.example.mybatisplus.po.prod.SubjectPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    SubjectMapper subjectMapper;

    @Test
    void selectProdTest() {
        List<SubjectPO> pos = subjectMapper.queryAll();
        pos.forEach((po) -> System.out.println(">>" + po));
    }


    @Autowired
    EnumTableMapper enumTableMapper;

    @Test
    void selectDevTest() {
        List<EnumTablePO> pos = enumTableMapper.queryAll();
        pos.forEach((po) -> System.out.println(">>" + po));
    }


    @Autowired
    UserMapper userMapper;

    @Test
    void selectUserTest() {
        List<UserPO> userPOS = userMapper.getAll();
        userPOS.forEach((po) -> System.out.println(">>" + po));
    }

}
