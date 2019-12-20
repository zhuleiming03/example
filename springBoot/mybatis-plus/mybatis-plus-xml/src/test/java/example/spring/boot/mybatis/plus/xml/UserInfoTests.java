package example.spring.boot.mybatis.plus.xml;

import example.spring.boot.mybatis.plus.xml.enums.UserSexEnum;
import example.spring.boot.mybatis.plus.xml.mapper.UserInfoMapper;
import example.spring.boot.mybatis.plus.xml.po.UserInfoPO;
import example.spring.boot.mybatis.plus.xml.service.UserService;
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
    UserService userService;

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

    @Test
    void capabilityTest() {
        List<UserInfoPO> userPOList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            UserInfoPO po = initUserPO();
            po.setCardNo(po.getCardNo() + i);
            userPOList.add(po);
        }

        //批量插入数据
        StopWatch stopwatch = new StopWatch();

        stopwatch.start();
        mapper.batchInsert(userPOList);
        stopwatch.stop();
        System.out.println(">>method 1:"+ stopwatch.getTotalTimeSeconds());

        stopwatch.start();
        userService.saveBatch(userPOList);
        stopwatch.stop();
        System.out.println(">>method 2:"+ stopwatch.getTotalTimeSeconds());
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
