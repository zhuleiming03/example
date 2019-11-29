import org.junit.Test;

import java.time.LocalTime;

public class LocalTimeTest {

    @Test
    public void initLocalTime() {

        LocalTime now = LocalTime.now();
        System.out.println(">>当前时间:" + now);

        LocalTime nowSimple = LocalTime.now().withNano(0);
        System.out.println(">>无毫秒的当期时间:" + nowSimple);

        LocalTime zero = LocalTime.of(4, 45, 50);
        System.out.println(">>时分秒指定时间:" + nowSimple);

        LocalTime mid = LocalTime.parse("12:13:14");
        System.out.println(">>字符串指定时间:" + nowSimple);
    }
}
