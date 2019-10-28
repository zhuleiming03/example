package com.example.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {

    /**
     * 时间输出格式
     */
    @Test
    public void printDateTest(){
        //完整时间
        System.out.printf("转换符：c,值：%tc", new Date());
        System.out.println();
        //取日期部分
        System.out.printf("转换符：F,值：%tF", new Date());
        System.out.println();
        //取时间部分
        System.out.printf("转换符：T,值：%tT", new Date());
        System.out.println();
        //年
        System.out.printf("转换符：Y,值：%tY", new Date());
        System.out.println();
        //月
        System.out.printf("转换符：m,值：%tm", new Date());
        System.out.println();
        //日
        System.out.printf("转换符：d,值：%td", new Date());
        System.out.println();
        //时
        System.out.printf("转换符：H,值：%tH", new Date());
        System.out.println();
        //分
        System.out.printf("转换符：M,值：%tM", new Date());
        System.out.println();
        //秒
        System.out.printf("转换符：S,值：%tS", new Date());
        System.out.println();
    }

    /**
     * jdk7 日期表达
     */
    @Test
    public void simpleDateFormatTest() {

        Date currentDate = new Date();
        System.out.println("当前时间:" + currentDate);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateString = simpleDateFormat.format(currentDate);
        System.out.println("当前时间格式化后:" + currentDateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR, 3);
        String afterThreeHourString = simpleDateFormat.format(calendar.getTime());
        System.out.println("当前时间加3小时格式化后:" + afterThreeHourString);

        if (currentDateString.compareTo(afterThreeHourString) < 0) {
            System.out.println("时间比较：当前时间小于修改后时间");
        } else {
            System.out.println("时间比较：当前时间大于等于修改后时间");
        }
    }

    /**
     * jdk8 日期表达
     */
    @Test
    public void localDateTimeTest() {

        LocalDateTime currentDate = LocalDateTime.now();
        System.out.println("当前时间：" + currentDate);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间格式化后：" + currentDate.format(fmt));

        LocalDateTime previousYear = currentDate.minusYears(1);
        System.out.println("当前时间前一年：" + previousYear.format(fmt));

        LocalDateTime previousWeek = currentDate.minusWeeks(1);
        System.out.println("当前时间前一周：" + previousWeek.format(fmt));

        LocalDateTime afterDay = currentDate.minusDays(-1);
        System.out.println("当前时间后一天：" + afterDay.format(fmt));

        LocalDateTime afterHour = currentDate.plusHours(1);
        System.out.println("当前时间后一小时：" + afterHour.format(fmt));

        LocalDateTime currentMonth15 = currentDate.withDayOfMonth(15);
        System.out.println("当月15号：" + currentMonth15.format(fmt));

        LocalDateTime currentDayDec = currentDate.withMonth(12);
        System.out.println("十二月当天：" + currentDayDec.format(fmt));

        String tagDateTimeString = "2019-01-25 12:00:01";
        LocalDateTime tagDateTime = LocalDateTime.parse(tagDateTimeString, fmt);
        int days = (int) Duration.between(tagDateTime, currentDate).toDays();
        System.out.println("当前时间与2019-01-25 12:00:01 相差日（不忽略时间）：" + days);
    }

    /**
     * 星期计算
     */
    @Test
    public void weekTest() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse("2019-10-20 00:00:00", fmt);
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);

        System.out.println("当前第" + date.get(weekFields.weekOfYear()) + "周");
        System.out.println("星期：" + date.getDayOfWeek().getValue());
        System.out.println("开始：" + date.minusDays(date.getDayOfWeek().getValue() - 1));
        System.out.println("结束：" + date.minusDays(date.getDayOfWeek().getValue() - 7));

    }

}
