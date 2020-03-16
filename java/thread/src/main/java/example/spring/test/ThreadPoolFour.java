package example.spring.test;

import java.util.concurrent.*;

public class ThreadPoolFour {

    public static void main(String[] args) {
        //创建一个单线程化的线程池
        //ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        ExecutorService pool=new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 10; i++) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //结果依次输出，相当于顺序执行各个任务
                        System.out.println(Thread.currentThread().getName() + "正在被执行,打印的值是:" + index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
