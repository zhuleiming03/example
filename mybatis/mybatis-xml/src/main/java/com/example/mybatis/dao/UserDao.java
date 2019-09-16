package com.example.mybatis.dao;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDao {

    public List<UserPO> getUsers() {
        List<UserPO> users = userMapper.selectAll();
        return users;
    }

    public UserPO getUser(Long id) {
        return userMapper.selectById(id);
    }

    public Integer save(UserPO user) {
        return userMapper.insert(user);
    }

    public Integer update(UserPO user) {
        return userMapper.update(user);
    }

    public Integer delete(Long id) {
        return userMapper.delete(id);
    }

    private final UserMapper userMapper;
}
