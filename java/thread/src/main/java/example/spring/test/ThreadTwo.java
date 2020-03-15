package example.spring.test;

import example.spring.thread.Rabbit;
import example.spring.thread.Tortoise;

public class ThreadTwo {

    /**
     * 启动两个线程对象
     * @param args
     */
    public static void main(String[] args) {

        //创建子类对象
        Rabbit rab = new Rabbit();
        Tortoise tor = new Tortoise();

        //调用start方法
        rab.start();//不要调用 run 方法，不然就是普通的方法调用
        tor.start();

        for (int i = 0; i < 2; i++) {
            System.out.println("main==>" + i);
        }
    }
}
