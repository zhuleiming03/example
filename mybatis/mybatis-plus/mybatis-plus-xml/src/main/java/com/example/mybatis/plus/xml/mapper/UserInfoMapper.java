package com.example.mybatis.plus.xml.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.plus.xml.po.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfoPO> {

    void crateTable();

    List<UserInfoPO> selectAl();
}
