package com.example.mybatis.mapper.test;

import com.example.mybatis.po.UserPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserTestMapper {

    @Select("SELECT user_name,pass_word,user_sex FROM users ")
    @Results({
            //@Result(property = "userSex", column = "user_sex", jdbcType = JdbcType.VARCHAR, javaType = UserSexEnum.class),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "pass_word")
    })
    List<UserPO> getAll();

    @Select("SELECT id,user_name,pass_word,user_sex,is_valid,gmt_create,gmt_modified FROM users WHERE user_name = #{name}")
    @Results({

            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "pass_word"),
            //@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "valid", column = "is_valid"),
            @Result(property = "createTime", column = "gmt_create"),
            @Result(property = "modifiedTime", column = "gmt_modified")
    })
    UserPO getByName(String name);

    @Insert("INSERT INTO users(user_name,pass_word,user_sex,is_valid) VALUES(#{userName}, #{passWord}, #{userSex}, #{valid})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    Integer insert(UserPO user);

    @Update("UPDATE users SET pass_word = #{passWord},gmt_modified = #{modifiedTime} WHERE id = #{id}")
    Integer update(UserPO user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    Integer delete(Long id);
}
