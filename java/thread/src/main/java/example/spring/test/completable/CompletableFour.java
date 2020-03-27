package example.spring.test.completable;

import example.spring.service.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFour {

    /**
     * 异步执行 taskBird、taskLion 后将结果相乘
     *
     * @param args
     */
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 4, 5);

        ExecutorService pool = new ThreadPoolExecutor(Math.min(numbers.size(), 100),
                100,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        List<CompletableFuture<Integer>> futureResult = numbers
                .stream()
                //异步调用方法 taskLion
                .map(integer -> CompletableFuture.supplyAsync(
                        () -> Utils.taskLion(integer), pool)
                        .thenCombine(
                                //异步调用方法 taskBird
                                CompletableFuture.supplyAsync(
                                        () -> Utils.taskBird(integer)),
                                (i, j) -> i * j
                        )
                )
                .collect(Collectors.toList());

        futureResult.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);

        pool.shutdown();
    }
}
