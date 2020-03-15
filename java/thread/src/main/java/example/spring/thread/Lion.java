package example.spring.thread;

public class Lion extends Thread {

    @Override
    public void run() {

        Thread self = Thread.currentThread();
        self.setName("lion");

        System.out.println("----------------------------------");
        System.out.println("线程是否处于活动状态: " + self.isAlive());
        System.out.println("线程名称: " + self.getName());
        System.out.println("线程的优先级: " + self.getPriority());
        System.out.println("线程是否守护线程: " + self.isDaemon());
        System.out.println("线程详情: " + self.toString());
        System.out.println("线程ID: " + self.getId());
    }
}
