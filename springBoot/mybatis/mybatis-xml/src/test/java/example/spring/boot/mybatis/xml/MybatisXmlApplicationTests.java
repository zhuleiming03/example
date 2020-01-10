package example.spring.boot.mybatis.xml;

import example.spring.boot.mybatis.xml.enums.UserSexEnum;
import example.spring.boot.mybatis.xml.mapper.UserMapper;
import example.spring.boot.mybatis.xml.po.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisXmlApplicationTests {

    @Test
    void contextLoads() {
        //初始化数据库操作，创建表
        userMapper.initTable();
        System.out.println(">>:初始化数据库完成");

        //新增测试
        UserPO po = initUserPO();
        int result_add = userMapper.insert(po);
        if (result_add > 0) {
            System.out.println(">>新增成功,结果：" + po);
        }
        assert result_add == 1;

        //查询单个用户测试
        UserPO po_copy = initUserPO();
        userMapper.insert(po_copy);
        UserPO po_query = userMapper.selectById(po_copy.getId());
        System.out.println(">>查询成功,结果：" + po_query);
        assert po_query != null;

        //更新测试
        po_copy.setCheapRate(0.19d);
        po_copy.setCardNo(100200201911289756L);
        int result_alter = userMapper.update(po_copy);
        if (result_alter > 0) {
            System.out.println(">>更新成功,结果：" + po_copy);
        }
        assert result_alter == 1;


        //查询用户Map测试
        Map<Long, UserPO> map = userMapper.selectMap();
        if (map.size() > 0) {
            System.out.println(">>Map查询结果如下：");
            map.forEach((k, v) -> {
                System.out.println(String.format("Key:%s,Value:%s", k, v));
            });
        }

        //删除测试
        int result_remove = userMapper.delete(po.getId());
        if (result_remove > 0) {
            System.out.println(">>删除成功");
        }
        assert result_remove == 1;

        //查询用户List测试
        List<UserPO> list = userMapper.selectList();
        if (list.size() > 0) {
            System.out.println(">>List查询结果如下：");
            for (UserPO u : list) {
                System.out.println(u);
            }
        }
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

    @Resource
    UserMapper userMapper;
}
