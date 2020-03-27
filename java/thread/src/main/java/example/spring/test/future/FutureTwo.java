package example.spring.test.future;

import example.spring.service.Utils;
import example.spring.thread.Bird;

import java.time.LocalTime;
import java.util.concurrent.*;

public class FutureTwo {

    /**
     * 通过创建线程池来返回线程的结果
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //创建线程池
        ExecutorService pool = new ThreadPoolExecutor(3,
                3,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        Future<Integer> one = pool.submit(new Bird());
        Future<Integer> two = pool.submit(new Bird());
        Future<Integer> three = pool.submit(new Bird());

        //主进程执行其他任务2秒
        System.out.println(String.format("%s [%s] begin other thing",
                LocalTime.now(), Thread.currentThread().getName()));
        Utils.delay(2L);
        System.out.println(String.format("%s [%s] end other thing",
                LocalTime.now(), Thread.currentThread().getName()));

        System.out.println(String.format("%s [%s] thread one result: %s",
                LocalTime.now(), Thread.currentThread().getName(),
                one.get(3, TimeUnit.SECONDS)));
        System.out.println(String.format("%s [%s] thread two result: %s",
                LocalTime.now(), Thread.currentThread().getName(),
                two.get(3, TimeUnit.SECONDS)));
        System.out.println(String.format("%s [%s] thread three result: %s",
                LocalTime.now(), Thread.currentThread().getName(),
                three.get(3, TimeUnit.SECONDS)));

        //关闭线程池
        pool.shutdownNow();
    }
}
