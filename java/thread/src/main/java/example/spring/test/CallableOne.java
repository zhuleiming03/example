package example.spring.test;

import example.spring.thread.Cat;

import java.util.concurrent.FutureTask;

public class CallableOne {

    /**
     * 启动一个有返回值的线程对象
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Cat cat = new Cat();
        FutureTask<String> futureTask = new FutureTask<>(cat);
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "==>" + i);
            if (i == 2) {
                new Thread(futureTask, "cat").start();
            }
        }
        System.out.println("Callable 线程返回值：" + futureTask.get());
    }
}
