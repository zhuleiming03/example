package example.spring.boot.mybatis.plus.xml.mulidatasource;

import example.spring.boot.mybatis.plus.xml.mulidatasource.enums.UserSexEnum;
import example.spring.boot.mybatis.plus.xml.mulidatasource.mapper.mssql.UserMapper;
import example.spring.boot.mybatis.plus.xml.mulidatasource.po.mssql.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class MssqlTest {

    /**
     * 初始化数据库后进行增删改查操作
     */
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

        //批量插入更新查询测试
        batchTest();
    }

    @Autowired
    private UserMapper mapper;

    private void batchTest() {

        //初始化数据
        List<UserPO> userPOList = new LinkedList<>();
        UserPO po1 = initUserPO();
        userPOList.add(po1);
        UserPO po2 = initUserPO();
        po2.setName("宇智波佐助");
        po2.setBirthday(LocalDate.of(2000, 7, 23));
        po2.setCheapRate(0.8d);
        po2.setBalance(new BigDecimal(4324432.42f));
        po2.setCardNo(100200201911280019L);
        userPOList.add(po2);
        UserPO po3 = initUserPO();
        po3.setName("春野樱");
        po3.setBirthday(LocalDate.of(2000, 3, 28));
        po3.setCheapRate(0.75d);
        po3.setBalance(new BigDecimal(105.31f));
        po3.setSex(UserSexEnum.FAMALE);
        po3.setCardNo(100200201911280020L);
        userPOList.add(po3);

        //批量插入数据
        mapper.batchInsert(userPOList);

        System.out.println(">>批量插入数据:");
        userPOList.forEach((u) -> {
            System.out.println(">>" + u);
        });


        //批量查询数据
        List<UserPO> poList = mapper.selectAll();

        //批量更新数据
        poList.forEach((u) -> {
            u.setCheapRate(u.getCheapRate() * 0.8f);
            u.setBalance(u.getBalance().subtract(new BigDecimal(99)));
        });
        mapper.batchUpdate(poList);

        System.out.println(">>批量更新数据:");
        poList.forEach((u) -> {
            System.out.println(">>" + u);
        });

    }

    private UserPO initUserPO() {
        //新增记录
        short age = 18;
        BigDecimal balance = new BigDecimal(1582.62f);
        UserPO po = new UserPO();
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
