package example.spring.test;

import example.spring.thread.*;

import java.util.concurrent.*;

public class ThreadEight {

    private static ExecutorService pool;

    public static void main(String[] args) throws Exception {

        pool = new ThreadPoolExecutor(1,
                3,
                1000,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        pool.execute(new Rabbit());
        pool.execute(new Lion());
        pool.execute(new Tortoise());

        pool.shutdown();
    }
}
