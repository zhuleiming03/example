package example.spring.test.threadPool;

import example.spring.service.Utils;

import java.util.concurrent.*;

public class ThreadPoolTwo {

    public static void main(String[] args) {
        // 创建一个可重用固定个数的线程池
        // 因为线程池大小为3，每个任务输出打印结果后sleep 2秒，所以每两秒打印3个结果。
        //ExecutorService pool = Executors.newFixedThreadPool(3);

        // workQueue 为LinkedBlockingQueue（无界阻塞队列），
        // 队列最大值为Integer.MAX_VALUE。如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。
        // 因为队列很大，很有可能在拒绝策略前，内存溢出。是其劣势；
        ExecutorService pool = new ThreadPoolExecutor(3,
                3,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    // 打印正在执行的缓存线程信息
                    System.out.println(Thread.currentThread().getName()
                            + "正在被执行");
                    Utils.delay(1L);
                }
            });
        }

        pool.shutdown();
    }
}
