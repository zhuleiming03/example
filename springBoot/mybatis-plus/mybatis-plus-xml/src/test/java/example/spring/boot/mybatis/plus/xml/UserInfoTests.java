package example.spring.boot.mybatis.plus.xml;

import example.spring.boot.mybatis.plus.xml.enums.UserSexEnum;
import example.spring.boot.mybatis.plus.xml.mapper.UserInfoMapper;
import example.spring.boot.mybatis.plus.xml.po.UserInfoPO;
import example.spring.boot.mybatis.plus.xml.service.UserService;
import example.spring.boot.mybatis.plus.xml.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class UserInfoTests {

    @Autowired
    UserInfoMapper mapper;

    @Autowired
    UserServiceImpl userService;

    /**
     * 增删改查测试
     */
    @Test
    void baseTest() {

        //初始化数据库操作，创建表
        mapper.crateTable();
        System.out.println(">>:初始化数据库完成");

        //新增记录
        UserInfoPO po = initUserPO();
        mapper.insert(po);
        System.out.println(">>:新增记录 " + po);

        //修改记录
        po.setCheapRate(0.85d);
        mapper.updateById(po);
        System.out.println(">>:修改记录 " + po);

        //删除记录
        System.out.println(">>:删除记录ID " + po.getId());
        mapper.deleteById(po.getId());

        //两种批量插入测试
        batchInsert();

        //查询所有记录
        System.out.println(">>:现存数据库记录如下 --------------- ");
        System.out.println(">>:" + mapper.selectAll());
    }

    void batchInsert() {
        List<UserInfoPO> userPOListA = new LinkedList<>();
        for (int i = 0; i < 233; i++) {
            UserInfoPO po = initUserPO();
            po.setCardNo(po.getCardNo() + i);
            userPOListA.add(po);
        }

        List<UserInfoPO> userPOListB = new LinkedList<>();
        for (int i = 0; i < 233; i++) {
            UserInfoPO po = initUserPO();
            po.setCardNo(po.getCardNo() + i);
            userPOListB.add(po);
        }

        //批量插入数据
        StopWatch stopwatch = new StopWatch();

        System.out.println(">>method 1 最大可输入2100个参数，只支持262个UserInfoPO");
        stopwatch.start("method-1:");
        mapper.batchInsert(userPOListA);
        stopwatch.stop();

        System.out.println(">>method 2 不支持自增长表 ( Error:type = IdType.AUTO)");
        stopwatch.start("method-2:");
        userService.saveBatch(userPOListB);
        stopwatch.stop();

        System.out.println(stopwatch.prettyPrint());
    }

    private UserInfoPO initUserPO() {
        //新增记录
        short age = 18;
        BigDecimal balance = new BigDecimal(1582.62f);
        UserInfoPO po = new UserInfoPO();
        po.setCardNo(100200201911280018L);
        po.setName("漩涡鸣人");
        po.setSex(UserSexEnum.MALE);
        po.setAge(age);
        po.setCheapRate(0.95d);
        po.setBalance(balance);
        po.setBirthday(LocalDate.of(2000, 10, 10));
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));
        po.setVaild(true);
        return po;
    }

}
