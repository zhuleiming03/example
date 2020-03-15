package example.spring.thread;

public class Rabbit extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("兔子跑了%d步", i));
        }
    }
}
