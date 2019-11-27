package com.example.mybatisplus.mapper.mysql;

import com.example.mybatisplus.po.mysql.UserPO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {

    @Select("SELECT user_name,pass_word,user_sex FROM users ")
    @Results({
            //@Result(property = "userSex", column = "user_sex", jdbcType = JdbcType.VARCHAR, javaType = UserSexEnum.class),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "pass_word")
    })
    List<UserPO> getAll();
}
