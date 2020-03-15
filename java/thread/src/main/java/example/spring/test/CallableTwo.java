package example.spring.test;

import example.spring.thread.Cat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTwo {

    /**
     * 通过创建线程池来返回线程的结果
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Cat cat = new Cat();
        Future<String> result = null;
        for (int i = 0; i < 10; i++) {
            System.out.println("Main==>" + i);
            if (i == 2) {
                result = pool.submit(cat);
                //关闭线程池
                pool.shutdownNow();
            }
        }
        System.out.println("返回值为：" + result.get());
    }
}
