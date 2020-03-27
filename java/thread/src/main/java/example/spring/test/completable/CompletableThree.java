package example.spring.test.completable;

import example.spring.service.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableThree {

    /**
     * 异步执行 taskCat 后再异步执行 taskDog
     * @param args
     */
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 4, 5);

        ExecutorService pool = new ThreadPoolExecutor(Math.min(numbers.size(), 100),
                100,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        List<CompletableFuture<String>> futureResult = numbers
                .stream()
                //异步调用方法 taskCat
                .map(future -> CompletableFuture.supplyAsync(
                        () -> Utils.taskCat(future), pool))
                //同步处理 taskCat 返回值
                .map(s -> s.thenApply(String::length))
                //用处理好的值异步调用方法 taskDog
                .map(future -> future.thenCompose(integer -> CompletableFuture.supplyAsync(
                        () -> Utils.taskDog(integer), pool
                )))
                .collect(Collectors.toList());

        futureResult.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);

        pool.shutdown();
    }
}
