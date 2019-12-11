package example.spring.boot.mybatis.plus.xml.mulidatasource.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.spring.boot.mybatis.plus.xml.mulidatasource.po.mysql.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsrMapper extends BaseMapper<UserPO> {

    void crateTable();

}
