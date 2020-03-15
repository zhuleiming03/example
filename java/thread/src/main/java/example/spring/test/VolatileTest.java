package example.spring.test;

public class VolatileTest extends Thread {
    private static  boolean flag = false;

    @Override
    public void run() {
        while (!flag) {
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws Exception {
        new VolatileTest().start();
        Thread.sleep(2000);
        flag = true;
    }
}
