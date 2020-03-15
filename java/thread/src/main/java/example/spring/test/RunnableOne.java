package example.spring.test;

import example.spring.thread.Cattle;

public class RunnableOne {

    /**
     * 启动一个runnable实例对象
     *
     * @param args
     */
    public static void main(String[] args) {

        //1.创建 发送消息实例
        Cattle cattle = new Cattle();

        //2.创建静态代理+发送消息实例引用
        Thread cow = new Thread(cattle, "cow");
        Thread bull = new Thread(cattle, "bull");
        Thread ox = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is thread name : " + Thread.currentThread().getName());
            }
        });

        //3.调用.start(); 启动线程
        cow.start();
        bull.start();
        ox.start();
    }
}
