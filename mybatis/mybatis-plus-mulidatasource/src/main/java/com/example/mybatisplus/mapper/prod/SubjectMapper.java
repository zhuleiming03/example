package com.example.mybatisplus.mapper.prod;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.po.prod.SubjectPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubjectMapper extends BaseMapper<SubjectPO> {

    List<SubjectPO> queryAll();
}
