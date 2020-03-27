package example.spring.test.thread;

import example.spring.thread.Tortoise;

public class ThreadFive {

    /**
     * yield 重置CPU 争夺状态 是个坑，不能做进程堵塞用
     *
     * @param args
     */
    public static void main(String args[]) {

        //创建子类对象
        Tortoise tortoise = new Tortoise();

        //调用start方法
        tortoise.start();

        //放弃 CPU 时间片，重新竞争，不一定做到让步
        Thread.yield();
        System.out.println("race end");
    }
}
