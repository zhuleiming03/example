package com.example.mybatis;

import com.example.mybatis.mapper.EnumTableMapper;
import com.example.mybatis.po.EnumTablePO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    EnumTableMapper mapper;

    EnumTablePO initPo() {
        EnumTablePO po = new EnumTablePO();
        po.setIdentity(false);
        po.setTableCode(798625888);
        po.setTableName("DeductPlatform");
        po.setSchemasCode(1);
        po.setSchemasName("dbo");
        po.setVaild(true);
        return po;
    }

    @Test
    void insertTest() {
        EnumTablePO po = initPo();
        mapper.insert(po);
        System.out.println(">>insert:" + po);
    }

    @Test
    void deleteTest() {
        EnumTablePO po = initPo();
        mapper.insert(po);
        System.out.println(">>insert:" + po);
        mapper.deleteById(po.getId());
        System.out.println(">>delete:" + po);
    }

    @Test
    void updateTest() {
        EnumTablePO po = initPo();
        mapper.insert(po);
        System.out.println(">>insert:" + po);
        po.setIdentity(true);
        mapper.updateById(po);
        System.out.println(">>update:" + po);
    }

    @Test
    void selectTest() {
        List<EnumTablePO> pos = mapper.queryAll();
        pos.forEach((po) -> System.out.println(">>" + po));
    }

}
