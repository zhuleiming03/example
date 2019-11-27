package com.example.mybatisplus.mapper.dev;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.po.dev.EnumTablePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EnumTableMapper extends BaseMapper<EnumTablePO> {

    List<EnumTablePO> queryAll();
}