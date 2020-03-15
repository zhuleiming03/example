package example.spring.thread;

public class Tortoise extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(String.format("乌龟跑了%d步", i));
        }
    }
}
