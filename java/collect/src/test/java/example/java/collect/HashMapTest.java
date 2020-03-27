package example.java.collect;

import example.java.collect.domain.Bill;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    @Test
    public void hashCodeTest() {

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
                        k, v, k.hashCode(), v.hashCode());
            } else {
                msg = String.format("key: %10s , value: %4s", k, v);
            }
            System.out.println(msg);
            msg.hashCode();
        });
    }
}
