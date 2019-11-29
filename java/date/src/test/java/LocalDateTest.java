import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {

    @Test
    public void initLocalDateTest() {

        //当前时间
        LocalDate today = LocalDate.now();
        System.out.println(">>当前日期:" + today);

        //年月日指定时间
        LocalDate dateYmd = LocalDate.of(1990, 5, 15);
        System.out.println(">>年月日指定日期:" + dateYmd);

        //根据文本转化时间
        LocalDate dateString = LocalDate.parse("2019-03-28");
        System.out.println(">>文本转化日期:" + dateString);
    }

    @Test
    public void calculateLocalDateTest() {

        //当前时间
        LocalDate today = LocalDate.now();
        System.out.println(">>当前日期:" + today);

        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(">>月首:" + firstDayOfThisMonth);

        // 取本月第3天：
        LocalDate thirdDayOfThisMonth = today.withDayOfMonth(3);
        System.out.println(">>本月第3天:" + thirdDayOfThisMonth);

        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(">>月尾:" + lastDayOfThisMonth);

        // 取下一天：
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        System.out.println(">>" + lastDayOfThisMonth + " 后一天：" + firstDayOfNextMonth);
    }
}
