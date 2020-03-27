package example.spring.test.thread;

import example.spring.service.Utils;
import example.spring.thread.Dog;

import java.time.LocalTime;

public class ThreadThree {

    /**
     * 停止一个线程
     * @param args
     */
    public static void main(String[] args) {
        Dog dog = new Dog();

        System.out.println(LocalTime.now() + " : 启动一个后台进程");
        dog.start();

        //主进程休眠1秒
        Utils.delay(1L);

        System.out.println(LocalTime.now() + " : 停止一个后台进程");
        dog.interrupt();
    }
}
