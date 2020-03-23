package example;

import example.service.MeasureService;
import org.junit.Test;
import org.junit.internal.runners.SuiteMethod;

import java.util.function.UnaryOperator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelTest {

    @Test
    public void capacityTest() {

        long count = 10_000_000L;

        System.out.println("method【for】need time is "
                + MeasureService.measure(ParallelTest::addFor, count));

        System.out.println("method【sequential iterate】need time is "
                + MeasureService.measure(ParallelTest::addSequentialIterate, count));

        System.out.println("method【sequential rangeClosed】need time is "
                + MeasureService.measure(ParallelTest::addSequentialRanged, count));

        System.out.println("method【parallel iterate】need time is "
                + MeasureService.measure(ParallelTest::addParallelIterate, count));

        System.out.println("method【sequential rangeClosed】need time is "
                + MeasureService.measure(ParallelTest::addParallelRanged, count));
    }

    private static long addFor(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    private static Long addSequentialIterate(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).
                reduce(0L, Long::sum);
    }

    private static Long addParallelIterate(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().
                reduce(0L, Long::sum);
    }

    private static Long addSequentialRanged(long n) {
        return LongStream.rangeClosed(1L, n).
                reduce(0L, Long::sum);
    }

    private static Long addParallelRanged(long n) {
        return LongStream.rangeClosed(1L, n).parallel().
                reduce(0L, Long::sum);
    }
}
