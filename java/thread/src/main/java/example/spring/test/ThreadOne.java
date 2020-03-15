package example.spring.test;

import example.spring.thread.Lion;

public class ThreadOne {


    /**
     * 启动一个线程，查看线程相关属性
     * @param args
     */
    public static void main(String[] args) {

        //创建子类对象
        Lion lion = new Lion();

        //调用start方法
        //不要调用 run 方法，不然就是普通的方法调用
        lion.start();
    }
}
