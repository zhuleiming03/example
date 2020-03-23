package example.service;

import java.util.function.Function;

public class MeasureService {

    public static long measure(Function<Long,Long> method,Long n) {
        long fastTest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = method.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastTest) {
                fastTest = duration;
            }
        }
        return fastTest;
    }
}
