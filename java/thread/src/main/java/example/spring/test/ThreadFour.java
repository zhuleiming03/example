package example.spring.test;

import example.spring.thread.Tortoise;

public class ThreadFour {

    /**
     * 阻塞一个进程
     * @param args
     */
    public static void main(String args[]) {

        //创建子类对象
        Tortoise tortoise = new Tortoise();

        //调用start方法
        tortoise.start();

        //等待 tortoise 执行完后 才能执行主线程
        try {
            tortoise.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("race end");
    }
}
