package example.spring.test.completable;

import example.spring.service.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class CompletableFive {

    /**
     * 异步执行 taskAnimal ,不等待其他线程结束 直接输出结果
     *
     * @param args
     */
    public static void main(String[] args) {

        List<String> animal = Arrays.asList(
                "Bird", "Cat", "Cattle", "Dog", "Fish", "Lion", "Rabbit", "Tortoise"
        );

        ExecutorService pool = new ThreadPoolExecutor(Math.min(animal.size(), 50),
                50,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        //异步查询流
        Stream<CompletableFuture<String>> futureStream = animal
                .stream()
                .map(future -> CompletableFuture.supplyAsync(
                        () -> Utils.taskAnimal(future), pool));

        //异步输出结果
        CompletableFuture[] futures = futureStream
                .map(f -> f.thenAccept(Utils::print))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();

        pool.shutdown();
    }
}
