package example.test;

import example.domain.Apple;
import example.domain.Pear;
import example.service.impl.FunctionService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FunctionTest {

    /**
     * part 2:
     * lambda
     * @param args
     */
    public static void main(String[] args) {

        // 布尔表达式
        System.out.println("------------ Predicate ----------------");
        testFilter();

        // 创建对象
        System.out.println("------------ Supplier -----------------");
        testProduct();

        // 消费一个对象
        System.out.println("------------ Consumer -----------------");
        testConsumer();

        // 从一个对象中选择/提取
        System.out.println("------------ Function -----------------");
        testMap();

        // 合并两个值
        System.out.println("------------ IntBinaryOperator --------");
        testMerge();

        // 比较两个对象
        System.out.println("------------ Comparable ---------------");
        testCompare();
    }

    /**
     * Comparable<T>
     * int compare(T o1, T o2);
     */
    private static void testCompare() {
        Pear newPear = new Pear(53.14d);
        Pear oldPear = new Pear(34.56d);

        System.out.println("new pear compare to old pear result is " +
                FunctionService.compare(newPear, oldPear,
                        (i, j) -> i.getPrice().compareTo(j.getPrice())));
    }

    /**
     * IntBinaryOperator
     * int applyAsInt(int left, int right);
     */
    private static void testMerge() {
        System.out.println("41 * 14 = " +
                FunctionService.math(41, 14, (i, j) -> i * j));
    }

    /**
     * Function<T, R>
     * R apply(T t);
     */
    private static void testMap() {
        List<String> names = Arrays.asList("Sean", "Fairy", "Tom");
        Map<String, Integer> lengths = FunctionService.map(names, (String s) -> s.length());
        lengths.forEach((k, v) -> System.out.println(k + "'s length is " + v));
    }

    /**
     * Consumer<T>
     * void accept(T t);
     */
    private static void testConsumer() {
        FunctionService.print("This is a consumer!",
                (String msg) -> System.out.println(msg));
    }

    /**
     * Supplier<T>
     * T get();
     */
    private static void testProduct() {
        Apple apple = FunctionService.create(() -> new Apple(14.5f, "red"));
        System.out.println(apple);
        Pear pear = FunctionService.create(() -> new Pear(67.41d));
        System.out.println(pear);
    }

    /**
     * Predicate<T>
     * boolean test(T t);
     */
    private static void testFilter() {

        //无装箱
        System.out.println("100 是否能被2整除： " +
                FunctionService.checkInt(100, i -> i % 2 == 0));

        //有装箱
        System.out.println("101 是否能被3整除： " +
                FunctionService.check(101, i -> i % 3 == 0));
    }
}
