package example;

import org.junit.Test;

import java.util.stream.IntStream;

public class DebugTest {

    @Test
    public void peek() {

        IntStream.of(1, 3, 5, 7, 9)
                .filter(i -> i > 2)
                .peek(x -> System.out.println("filter i: " + x))
                .map(i -> i + 2)
                .forEach(System.out::println);

    }
}
