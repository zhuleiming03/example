import org.junit.Test;

import java.time.LocalDate;

public class LocalDateTest {

    @Test
    public void initLocalDateTest() {

        //当前时间
        LocalDate today = LocalDate.now();
        System.out.println(">>当前时间:" + today);

        //年月日指定时间
        LocalDate dateYmd = LocalDate.of(1990, 5, 15);
        System.out.println(">>年月日指定时间:" + dateYmd);

        //根据文本转化时间
        LocalDate dateString = LocalDate.parse("2019-03-28");
        System.out.println(">>文本转化时间:" + dateString);
    }
}
