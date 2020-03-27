package example.spring.test.threadPool;

import java.time.LocalTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolThree {

    public static void main(String[] args) {
        //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
        //ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(3);

        System.out.println(LocalTime.now() + "----------- begin ------------");
        //延迟1秒后每3秒执行一次
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(LocalTime.now() + " 延迟1秒后每3秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
