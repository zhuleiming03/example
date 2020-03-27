package example.spring.test.threadPool;


import example.spring.service.Utils;

import java.util.concurrent.*;

public class ThreadPoolOne {

    public static void main(String[] args) {
        // 创建一个可缓存线程池
        // 线程池为无限大，当执行当前任务时上一个任务已经完成，会复用执行上一个任务的线程，而不用每次新建线程
        //ExecutorService pool = Executors.newCachedThreadPool();

        // keepAliveTime = 60s，线程空闲60s后自动结束。
        // workQueue 为 SynchronousQueue 同步队列，
        // 这个队列类似于一个接力棒，入队出队必须同时传递，
        // 因为CachedThreadPool线程创建无限制，不会有队列等待，所以使用SynchronousQueue；
        ExecutorService pool = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        for (int i = 0; i < 10; i++) {

            // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
            Utils.delay(1L);

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    // 打印正在执行的缓存线程信息
                    System.out.println(Thread.currentThread().getName()
                            + "正在被执行");
                    Utils.delay(2L);
                }
            });
        }

        pool.shutdown();
    }
}
