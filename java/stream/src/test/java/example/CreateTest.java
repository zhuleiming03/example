package example;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateTest {

    @Test
    public void rang() {

        IntStream one = IntStream.rangeClosed(1, 10).filter(n -> n % 2 == 0);
        System.out.println("整数1到10（含10）能被2整除的数合计：" + one.count());

        IntStream two = IntStream.range(1, 10).filter(n -> n % 2 == 0);
        System.out.println("整数1到10（不含10）能被2整除的数合计：" + two.count());
    }

    @Test
    public void init() {

        Stream<String> stringStream = Stream.of("Java 8", "C#", "Python");
        stringStream.forEach(System.out::println);

        int[] numbers = {6, 32, 53, 12};
        IntStream integerStream = Arrays.stream(numbers);
        System.out.println("整数集合合计：" + integerStream.sum());
    }

    @Test
    public void create() {

        System.out.println("----------- iterate -----------");
        Stream.iterate(2, n -> n * 2).limit(10).forEach(System.out::println);

        System.out.println("----------- generate -----------");
        Stream.generate(Math::random).limit(3).forEach(System.out::println);
    }
}
