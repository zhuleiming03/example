package com.example.java;

import com.example.java.enumerate.MonthEnum;
import com.example.java.enumerate.WeekEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTests {

    @Test
    public void WeekEnumTest() {

        if (WeekEnum.MONDAY.equals("MONDAY")) {
            System.out.println("WeekEnum.MONDAY.equals(\"MONDAY\") result: true ");
        } else {
            System.out.println("WeekEnum.MONDAY.equals(\"MONDAY\") result: false ");
        }

        if (WeekEnum.TUSEDAY.toString().equals("TUSEDAY")) {
            System.out.println("WeekEnum.TUSEDAY.toString().equals(\"TUSEDAY\") result: true ");
        } else {
            System.out.println("WeekEnum.TUSEDAY.toString().equals(\"TUSEDAY\") result: false ");
        }

        System.out.println("WeekEnum.valueOf(\"SUNDAY\") result: " + WeekEnum.valueOf("SUNDAY"));

        System.out.println("WeekEnum.values():");
        for (WeekEnum weekEnum : WeekEnum.values()) {
            System.out.println(weekEnum);
        }

    }

    @Test
    public void MonthEnumTest() {

        System.out.println("get enum value: " + MonthEnum.JANUARRY);
        System.out.println("get enum code: " + MonthEnum.FEBRUARY.getCode());
        System.out.println("get enum description: " + MonthEnum.FEBRUARY.getDescription());
        System.out.println("get enum description by code: " + MonthEnum.getValueByCode(12));
    }
}
