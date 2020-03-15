package example.spring.test;

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

        //主进程休眠3毫秒
        try {
            Thread.sleep(3L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(LocalTime.now() + " : 停止一个后台进程");
        dog.interrupt();
    }
}
