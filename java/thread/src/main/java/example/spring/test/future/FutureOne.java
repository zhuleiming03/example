package example.spring.test.future;

import example.spring.service.Utils;
import example.spring.thread.Cat;

import java.time.LocalTime;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureOne {

    /**
     * 启动一个有返回值的线程对象
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Cat cat = new Cat();
        FutureTask<String> futureTask = new FutureTask<>(cat);
        //cat 线程开始执行
        new Thread(futureTask, "cat").start();

        //主进程执行其他任务3秒
        Utils.delay(3L);

        //超时时间3秒
        System.out.println(String.format("%s [cat] 执行结果： %s", LocalTime.now(),
                futureTask.get(3, TimeUnit.SECONDS)));
    }
}
