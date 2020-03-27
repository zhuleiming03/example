import example.NextWorkDay;
import org.junit.Test;

import java.time.*;

import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class MathTest {

    @Test
    public void testDuration() {
        LocalTime time1 = LocalTime.of(12, 12, 31);
        LocalTime time2 = LocalTime.of(12, 13, 12);
        Duration timeSpace = Duration.between(time1, time2);
        System.out.println("相差秒数：" + timeSpace.getSeconds());
    }

    @Test
    public void testPeriod() {
        LocalDate date1 = LocalDate.of(2020, 3, 1);
        LocalDate date2 = LocalDate.of(2020, 1, 28);
        Period timeSpace = Period.between(date2, date1);
        System.out.println(String.format("相差年：%s ,月：%s ,日：%s",
                timeSpace.getYears(), timeSpace.getMonths(), timeSpace.getDays()));
    }

    @Test
    public void testAlter() {

        LocalDate today = LocalDate.now();

        // 加一天：
        LocalDate addDay = today.plusDays(1);
        System.out.println(">>" + today + " 加一天：" + addDay);

        // 减一个月：
        LocalDate subductionMonth = today.minusMonths(1);
        System.out.println(">>" + today + " 减一月：" + subductionMonth);

        LocalDate alterYear = today.withYear(2008);
        System.out.println(">>" + today + " 修改年：" + alterYear);
    }

    @Test
    public void testCustom() {
        LocalDate Friday = LocalDate.now().with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(">>" + Friday + " 加一个工作日：" + Friday.with(new NextWorkDay()));
    }
}
