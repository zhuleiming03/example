package example.spring.test.completable;

import example.spring.service.Utils;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class CompletableTwo {

    /**
     * 通过创建线程池来返回线程的结果,可动态分配线程个数
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {

        List<CompletableFuture<String>> pricFuture = animal
                .stream()
                .map(item -> CompletableFuture.supplyAsync(
                        () -> String.format("%s %s price is %d",
                                LocalTime.now(), item, Utils.taskBird(item.length())), executor
                ))
                .collect(Collectors.toList());

        List<String> reuslt = pricFuture
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        reuslt.forEach(System.out::println);
    }

    private static List<String> animal = Arrays.asList(
            "Bird", "Cat", "Cattle", "Dog", "Fish", "Lion", "Rabbit", "Tortoise"
    );

    private static final Executor executor =
            Executors.newFixedThreadPool(Math.min(animal.size(), 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });
}
