package com.example.frame.dao.mapper;

import com.example.frame.domain.po.UserPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {
    List<UserPO> selectAll();

    UserPO selectById(Long id);

    Integer insert(UserPO user);

    Integer update(UserPO user);

    Integer delete(Long id);
}
