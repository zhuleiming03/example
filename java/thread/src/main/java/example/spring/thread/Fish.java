package example.spring.thread;

import java.time.LocalTime;

public class Fish implements Runnable {

    private boolean end = false;

    private int num = 10;

    @Override
    public void run() {
        while (!end) {
            try {
                unsafe();   //线程不安全
                //use();    //线程安全，同步方法
                //eat();    //线程安全，同步块
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 同步块
     *
     * @throws InterruptedException
     */
    public void eat() throws InterruptedException {

        synchronized (Fish.class) {
            if (num <= 0) {
                end = true;
                return;
            }
            // 模拟延迟
            Thread.sleep(1_000L);
            System.out.println(String.format("%s[%s]食用了鱼，剩余：%2d",
                    LocalTime.now(), Thread.currentThread().getName(), --num));
        }
    }

    /**
     * 同步方法
     *
     * @throws InterruptedException
     */
    public synchronized void use() throws InterruptedException {

        if (num <= 0) {
            end = true;
            return;
        }
        // 模拟延迟
        Thread.sleep(1_000L);
        System.out.println(String.format("%s[%s]消耗了鱼，剩余：%2d",
                LocalTime.now(), Thread.currentThread().getName(), --num));
    }

    public void unsafe() throws InterruptedException {
        if (num <= 0) {
            end = true;
            return;
        }
        // 模拟延迟
        Thread.sleep(1_000L);
        String msg = String.format("%s[%s]消耗了鱼，剩余：%2d",
                LocalTime.now(), Thread.currentThread().getName(), --num);
        System.out.println(msg);
    }

}
