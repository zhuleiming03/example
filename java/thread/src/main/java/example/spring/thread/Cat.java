package example.spring.thread;

import java.util.concurrent.Callable;

public class Cat implements Callable<String> {

    @Override
    public String call() {
        int i = 0;
        for (; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "==>" + i);
        }
        return String.format("cat eat %d fish", i);
    }
}
