package example.test;

import example.domain.Pear;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodTest {

    /**
     * part 3:
     * 方法引用
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("--------- lambda等价的方法引用 --------------");
        testOne();
        System.out.println("--------- 构造函数的方法引用  ---------------");
        testTwo();
    }

    private static void testTwo() {

        //无参构造函数
        Supplier<Pear> supplier = Pear::new;
        Pear one = supplier.get();
        System.out.println(one);

        //有参构造函数
        Function<Double, Pear> function = Pear::new;
        Pear two = function.apply(46.6d);
        System.out.println(two);
    }

    private static void testOne() {

        List<Integer> numbers = Arrays.asList(1, 2, 3);
        //lambda
        numbers.forEach(s -> System.out.println(s));
        //方法引用
        numbers.forEach(System.out::println);
    }
}
