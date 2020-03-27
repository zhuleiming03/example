package example.spring.test.thread;


import example.spring.service.Utils;

public class ThreadSeven extends Thread {

    private static boolean flag = false;
    //private volatile static boolean flag = false;

    /**
     * volatile 会影响编译顺序
     * 不加关键字，编译器会修改赋值顺序
     * 比如这个例子 flag = true 被前置，线程永远空循环
     *
     * @param args
     */
    public static void main(String[] args) {
        new ThreadSeven().start();
        Utils.delay(1L);
        flag = true;
    }

    @Override
    public void run() {
        System.out.println(">>thread begin");
        int i = 0;
        while (!flag) {

        }
        System.out.println(">>thread end");
    }
}
