package example.spring.boot.mybatis.plus.xml.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.spring.boot.mybatis.plus.xml.po.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfoPO> {

    void crateTable();

    List<UserInfoPO> selectAll();

    void batchInsert(@Param("list") List<UserInfoPO> list);
}
