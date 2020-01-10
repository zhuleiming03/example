package example.spring.boot.mybatis.xml.mapper;

import example.spring.boot.mybatis.xml.po.UserPO;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    void initTable();

    List<UserPO> selectList();

    UserPO selectById(Long id);

    @MapKey("cardNo")
    Map<Long,UserPO> selectMap();

    Integer insert(UserPO user);

    Integer update(UserPO user);

    Integer delete(Long id);
}
