package com.example.mybatis.dao;

import com.example.mybatis.mapper.demo.UserDemoMapper;
import com.example.mybatis.mapper.test.UserTestMapper;
import com.example.mybatis.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDao {

    public List<UserPO> getUsers() {
        List<UserPO> userDemo = userDemoMapper.getAll();
        List<UserPO> users = userTestMapper.getAll();
        users.addAll(userDemo);
        return users;
    }

    public UserPO getUser(String name) {
        UserPO user = userTestMapper.getByName(name);
        if (user == null) {
            user = userDemoMapper.getByName(name);
            return user;
        } else {
            return user;
        }
    }

    private final UserTestMapper userTestMapper;

    private final UserDemoMapper userDemoMapper;
}
