package example.spring.boot.mybatis.plus.xml.mulidatasource.mapper.mysql;

import example.spring.boot.mybatis.plus.xml.mulidatasource.po.mysql.UserPO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsrMapper {

    @Select("SELECT user_name,pass_word,user_sex FROM users ")
    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "pass_word")
    })
    List<UserPO> getAll();
}
