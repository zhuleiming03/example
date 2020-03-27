package example.spring.test.completable;

import example.spring.service.Utils;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableOne {

    /**
     * 启动一个有返回值的线程对象
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        CompletableFuture<String> futurePrice = new CompletableFuture<>();
        // 线程开始执行
        new Thread(() -> {
            System.out.println(String.format("%s [son] query start", LocalTime.now()));
            Utils.delayRandom(1);
            System.out.println(String.format("%s [son] query end", LocalTime.now()));
            futurePrice.complete("Apple");
        }).start();

        //主进程执行其他任务2秒
        System.out.println(String.format("%s [main] something start", LocalTime.now()));
        Utils.delayRandom(2);
        System.out.println(String.format("%s [main] something end", LocalTime.now()));

        //超时时间3秒
        System.out.println(String.format("%s [main] query result： %s", LocalTime.now(),
                futurePrice.get(3, TimeUnit.SECONDS)));
    }
}
