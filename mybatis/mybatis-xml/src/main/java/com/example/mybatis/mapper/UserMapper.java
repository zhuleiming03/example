package com.example.mybatis.mapper;

import com.example.mybatis.po.UserPO;

import java.util.List;

public interface UserMapper {

    List<UserPO> selectAll();

    UserPO selectById(Long id);

    Integer insert(UserPO user);

    Integer update(UserPO user);

    Integer delete(Long id);
}
