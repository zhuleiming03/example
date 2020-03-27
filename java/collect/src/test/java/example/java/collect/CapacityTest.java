package example.java.collect;

import example.java.collect.domain.UserPO;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CapacityTest {

    @Test
    public void listAndMapLoopTest() {

        List<Integer> idsList = new LinkedList<>();
        List<UserPO> userList = new LinkedList<>();
        List<String> userNameList = new LinkedList<>();
        Map<Integer, Integer> idsMap = new HashMap<>();
        Map<Integer, UserPO> userMap = new HashMap<>();
        Map<Integer, String> userNameMap = new HashMap<>();

        //初始化数据
        for (int i = 0; i < 100000; i++) {
            if (i % 2 == 0) {
                idsList.add(i);
                idsMap.put(i, i);
            }
            if (i % 3 == 0) {
                UserPO po = new UserPO();
                po.setUserId(i);
                po.setUserName("漩涡鸣人影分身->" + i);
                userList.add(po);
                userMap.put(i, po);
            }
        }
        System.out.println("ids list count:" + idsList.size());
        System.out.println("user List count:" + userList.size());
        System.out.println("ids map count:" + idsMap.size());
        System.out.println("user Map count:" + userMap.size());


        StopWatch stopwatch = StopWatch.createStarted();
        for (Integer id : idsList) {
            for (UserPO po : userList) {
                if (id.equals(po.getUserId())) {
                    userNameList.add(po.getUserName());
                }
            }
        }
        stopwatch.stop();

        System.out.println(String.format("userNameList count:%d,耗时：%s",
                userNameList.size(), stopwatch.getTime(TimeUnit.MILLISECONDS)));

        stopwatch.reset();
        stopwatch.start();
        for (Integer id : idsMap.keySet()) {
            for (Map.Entry<Integer, UserPO> po : userMap.entrySet()) {
                if (id.equals(po.getKey())) {
                    userNameMap.put(id, po.getValue().getUserName());
                }
            }
        }
        stopwatch.stop();
        System.out.println(String.format("userNameMap count:%d,耗时：%s",
                userNameMap.size(), stopwatch.getTime(TimeUnit.MILLISECONDS)));

    }
}
