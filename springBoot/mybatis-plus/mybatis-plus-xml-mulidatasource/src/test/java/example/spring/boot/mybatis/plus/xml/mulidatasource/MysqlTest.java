package example.spring.boot.mybatis.plus.xml.mulidatasource;


import example.spring.boot.mybatis.plus.xml.mulidatasource.enums.UserSexEnum;
import example.spring.boot.mybatis.plus.xml.mulidatasource.mapper.mysql.UsrMapper;
import example.spring.boot.mybatis.plus.xml.mulidatasource.po.mysql.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@SpringBootTest
class MysqlTest {


    @Test
    void baseTest() {

        //初始化数据库操作，创建表
        mapper.crateTable();
        System.out.println(">>:初始化数据库完成");

        //新增测试
        UserPO po = initUserPO();
        int result = mapper.insert(po);
        if (result > 0) {
            System.out.println(">>新增成功,结果：" + po);
        }
        assert result == 1;

        //更新测试
        po.setId(1);
        po.setCheapRate(0.19d);
        result = mapper.updateById(po);
        if (result > 0) {
            System.out.println(">>更新成功,结果：" + po);
        }
        assert result == 1;

        //查询测试
        UserPO po_query = mapper.selectById(1);
        System.out.println(">>查询结果:" + po_query);
        assert po_query != null;

        //删除测试
        result = mapper.deleteById(1);
        if (result > 0) {
            System.out.println(">>删除成功");
        }
        assert result == 1;
    }


    @Autowired
    private UsrMapper mapper;

    private UserPO initUserPO() {
        //新增记录
        short age = 16;
        BigDecimal balance = new BigDecimal(382.62f);
        UserPO po = new UserPO();
        po.setCardNo(100200201911632018L);
        po.setName("間桐 桜");
        po.setSex(UserSexEnum.FAMALE);
        po.setAge(age);
        po.setCheapRate(0.95d);
        po.setBalance(balance);
        po.setBirthday(LocalDate.of(1988, 3, 2));
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));
        po.setVaild(true);
        return po;
    }

}
