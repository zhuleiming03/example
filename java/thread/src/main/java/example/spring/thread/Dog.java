package example.spring.thread;

import java.time.LocalTime;

public class Dog extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(LocalTime.now() + " : 这个后台进程");
        }
        System.out.println(LocalTime.now() + " : 后台进程中断");
    }
}
