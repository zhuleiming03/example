package example.spring.test.thread;

import example.spring.thread.Fish;

public class ThreadSix {

    /**
     * 线程并发，并共同消耗公有数据的三种场景
     *
     * @param args
     */
    public static void main(String args[]) {

        //创建并发线程
        Fish fish = new Fish();
        Thread person = new Thread(fish, "person");
        Thread cat = new Thread(fish, "cat");
        Thread dog = new Thread(fish, "dog");

        //调用start方法
        person.start();
        cat.start();
        dog.start();

    }
}
