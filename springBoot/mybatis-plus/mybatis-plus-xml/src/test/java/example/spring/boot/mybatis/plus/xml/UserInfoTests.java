package example.spring.boot.mybatis.plus.xml;

import example.spring.boot.mybatis.plus.xml.enums.UserSexEnum;
import example.spring.boot.mybatis.plus.xml.mapper.UserInfoMapper;
import example.spring.boot.mybatis.plus.xml.po.UserInfoPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@SpringBootTest
class UserInfoTests {

    @Autowired
    UserInfoMapper mapper;


    /**
     * 增删改查测试
     */
    @Test
    void baseTest() {

        //初始化数据库操作，创建表
        mapper.crateTable();
        System.out.println(">>:初始化数据库完成");

        //新增记录
        short age = 16;
        BigDecimal balance = new BigDecimal(1582.62f);
        UserInfoPO po_1 = new UserInfoPO();
        po_1.setCardNo(100200201911280018L);
        po_1.setName("漩涡鸣人");
        po_1.setSex(UserSexEnum.MALE);
        po_1.setAge(age);
        po_1.setCheapRate(0.95d);
        po_1.setBalance(balance);
        po_1.setBirthday(LocalDate.of(1995, 3, 5));
        po_1.setCreateTime(new Timestamp(System.currentTimeMillis()));
        po_1.setVaild(true);
        mapper.insert(po_1);
        System.out.println(">>:新增记录 " + po_1);

        //修改记录
        po_1.setCheapRate(0.85d);
        mapper.updateById(po_1);
        System.out.println(">>:修改记录 " + po_1);

        //删除记录
        System.out.println(">>:删除记录ID " + po_1.getId());
        mapper.deleteById(po_1.getId());

        //查询所有记录
        System.out.println(">>:现存数据库记录如下 --------------- ");
        System.out.println(">>:" + mapper.selectAl());
    }

}
