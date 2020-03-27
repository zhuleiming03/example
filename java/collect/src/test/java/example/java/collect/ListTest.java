package example.java.collect;

import example.java.collect.service.CatService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

    @Test
    public void testArrarList() {

        List<Integer> a = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = a1;
        List<Integer> a3 = new ArrayList<>();
        a1.addAll(a);
        a3.addAll(a);
        System.out.println("引用传递，结果相等：" + (a1 == a2));
        System.out.println("两个引用，结果不等：" + (a1 == a3));

        List<Integer> b1 = CatService.getSafeList(a1, 6);
        List<Integer> b2 = CatService.getUnSafeList(a1, Integer.valueOf(6));
        System.out.println("新建实例，结果不等：" + (a1 == b1));
        System.out.println("引用传递，结果相等：" + (a1 == b2));
    }

    @Test
    public void testArrarListThread() throws InterruptedException {

        List<String> safe = new ArrayList<>();
        List<String> unSafe = new ArrayList<>();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    CatService.getSafeList(safe, String.valueOf(j));
                    CatService.getUnSafeList(unSafe, String.valueOf(j));
                }
            });
            threads[i].start();
        }

        threads[9].join();
        System.out.println("safe size: " + safe.size());
        System.out.println("unSafe list size: " + unSafe.size());
    }
}
