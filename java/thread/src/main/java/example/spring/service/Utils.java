package example.spring.service;

import java.time.LocalTime;
import java.util.Random;

public class Utils {

    public static void delay(long second) {
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void delayRandom(Integer second) {
        try {
            Thread.sleep(new Random().nextInt(second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Integer taskBird(Integer time) {
        System.out.println(String.format("%s [%s] task bird-%s begin",
                LocalTime.now(), Thread.currentThread().getName(), time));
        delayRandom(time);
        System.out.println(String.format("%s [%s] task bird-%s end",
                LocalTime.now(), Thread.currentThread().getName(), time));
        return time;
    }

    public static Integer taskLion(Integer time) {
        System.out.println(String.format("%s [%s] task lion-%s begin",
                LocalTime.now(), Thread.currentThread().getName(), time));
        delayRandom(time);
        System.out.println(String.format("%s [%s] task lion-%s end",
                LocalTime.now(), Thread.currentThread().getName(), time));
        return time - 1;
    }

    public static String taskCat(Integer time) {
        System.out.println(String.format("%s [%s] task cat-%s begin",
                LocalTime.now(), Thread.currentThread().getName(), time));
        delayRandom(time);
        System.out.println(String.format("%s [%s] task cat-%s end",
                LocalTime.now(), Thread.currentThread().getName(), time));
        return "cat";
    }

    public static String taskDog(Integer time) {
        System.out.println(String.format("%s [%s] task dog-%s begin",
                LocalTime.now(), Thread.currentThread().getName(), time));
        delayRandom(time);
        System.out.println(String.format("%s [%s] task dog-%s end",
                LocalTime.now(), Thread.currentThread().getName(), time));
        return String.format("dog-%s", Thread.currentThread().getName());
    }

    public static String taskAnimal(String name) {
        System.out.println(String.format("%s [%s] task %s begin",
                LocalTime.now(), Thread.currentThread().getName(), name));
        delay(name.length());
        System.out.println(String.format("%s [%s] task %s end",
                LocalTime.now(), Thread.currentThread().getName(), name));
        return ">>This animal's name is " + name;
    }

    public static void print(String result) {
        System.out.println(String.format("%s [%s] result: %s ",
                LocalTime.now(), Thread.currentThread().getName(), result));
    }
}
