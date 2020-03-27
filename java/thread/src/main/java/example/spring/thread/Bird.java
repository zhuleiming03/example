package example.spring.thread;

import example.spring.service.Utils;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Callable;

public class Bird implements Callable<Integer> {

    @Override
    public Integer call() {
        int result = new Random().nextInt(5);
        Utils.delay(result);
        System.out.println(String.format("%s [%s] 随机时间：%d 秒", LocalTime.now(),
                Thread.currentThread().getName(), result));
        return result;
    }
}
