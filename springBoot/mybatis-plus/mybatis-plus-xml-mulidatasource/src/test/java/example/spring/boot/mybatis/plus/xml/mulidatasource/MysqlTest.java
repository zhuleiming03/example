package example.spring.boot.mybatis.plus.xml.mulidatasource;


import example.spring.boot.mybatis.plus.xml.mulidatasource.mapper.mysql.UsrMapper;
import example.spring.boot.mybatis.plus.xml.mulidatasource.po.mysql.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MysqlTest {

    @Autowired
    private UsrMapper mapper;

    @Test
     void getAllUserTest() {

        List<UserPO> poList = mapper.getAll();
        for (UserPO po : poList) {
            System.out.println(">>po:" + po);
        }
    }

    @Test
    void aatest(){
        System.out.println("This is a test");
    }


}
