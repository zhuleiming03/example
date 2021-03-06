package example.spring.boot.mybatis.plus.xml.mulidatasource.mapper.mssql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.spring.boot.mybatis.plus.xml.mulidatasource.po.mssql.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserPO> {

    void crateTable();

    List<UserPO> selectAll();

    void batchUpdate(@Param("list") List<UserPO> list);

    void batchInsert(@Param("list") List<UserPO> list);

}