package example.java.collect;

import example.java.collect.domain.Bill;
import org.junit.Test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {

    @Test
    public void testHashMap() {

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(3, "three");
        map.put(2, "two");

        System.out.println("HashMap 是有序的，但不是按照插入先后顺序排序的");
        map.forEach((k, v) -> System.out.printf("key:%s ,value: %s \n", k, v));
    }

    @Test
    public void testLinkedHashMap() {

        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "one");
        map.put(3, "three");
        map.put(2, "two");

        System.out.println("LinkedHashMap 是有序的，是按照插入先后顺序排序的");
        map.forEach((k, v) -> System.out.printf("key:%s ,value: %s \n", k, v));
    }

    @Test
    public void testIdentityHashMap() {

        Map<Integer, String> map = new IdentityHashMap<>();
        map.put(1, "one");
        map.put(3, "three");
        map.put(1, "one one");
        map.put(129, "test one");
        map.put(129, "test two");

        System.out.println("IdentityHashMap 是有序的，可以插入重复key(不同的引用)");
        map.forEach((k, v) -> System.out.printf("key:%s ,value: %s \n", k, v));
    }

    @Test
    public void testHashCode() {

        Bill A = new Bill(11);
        Bill B = new Bill(22);

        Map<Bill, Integer> map = new HashMap<>();
        map.put(null, null);
        map.put(A, A.getId());
        map.put(B, B.getId());

        map.forEach((k, v) -> {
            String msg = "";
            if (k != null) {
                msg = String.format("key: %10s , value: %4s , key hashCode: %4d ",
                        k, v, k.hashCode());
            } else {
                msg = String.format("key: %10s , value: %4s", k, v);
            }
            System.out.println(msg);
        });
    }
}
