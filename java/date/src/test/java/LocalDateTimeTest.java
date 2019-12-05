import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    @Test
    public void initLocalDateTimeTest() {

        //当前时间
        LocalDateTime today = LocalDateTime.now();
        System.out.println(">>当前日期:" + today);

        //年月日指定时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateYmd = LocalDateTime.of(1990, 5, 15, 12, 45, 21);
        System.out.println(">>年月日指定日期:" + formatter.format(dateYmd));

        //根据文本转化时间
        LocalDateTime dateString1 = LocalDateTime.parse("2019-03-28T15:12:11");
        System.out.println(">>文本转化日期1:" + dateString1);
        LocalDateTime dateString2 = LocalDateTime.parse("2019-03-28 15:12:11", formatter);
        System.out.println(">>文本转化日期2:" + dateString2);
    }
}
