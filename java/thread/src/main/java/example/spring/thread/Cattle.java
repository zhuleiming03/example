package example.spring.thread;

public class Cattle implements Runnable {

    @Override
    public void run() {
        System.out.println("This is thread name : " + Thread.currentThread().getName());
    }
}
