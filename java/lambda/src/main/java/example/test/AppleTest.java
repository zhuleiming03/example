package example.test;

import example.domain.Apple;
import example.service.ApplePredicate;
import example.service.impl.AppleRedImpl;
import example.service.impl.AppleService;

import java.util.Arrays;
import java.util.List;

public class AppleTest {

    /**
     * part 1:
     * 行为参数化 -> 匿名函数 -> lambda
     * @param args
     */
    public static void main(String[] args) {

        //行为参数化
        testOne();
        //匿名函数
        testTwo();
        //lambda 表达式
        testThree();
        testFour();
    }

    /**
     * 通过 lambda 实现
     * 需要接口和方法块
     */
    private static void testFour() {

        AppleService service = new AppleService();
        List<Integer> numbers = Arrays.asList(19, 4, 8, 9, 3);
        System.out.println("--------------------");
        System.out.println(String.format("整数合计：%s 个", numbers.size()));
        List<Integer> result = service.filter(numbers,
                (Integer i) -> i % 2 == 0);
        System.out.println(String.format("筛选出：%s 个", result.size()));
        result.forEach(System.out::println);
    }

    /**
     * 通过 lambda 实现
     * 需要接口和方法块
     */
    private static void testThree() {

        AppleService service = new AppleService();
        List<Apple> apples = service.getApples();
        System.out.println("--------------------");
        System.out.println(String.format("苹果合计：%s 个", apples.size()));
        List<Apple> result = service.appleFilter(apples,
                (Apple apple) -> "pink".equals(apple.getColor()));
        System.out.println(String.format("筛选出：%s 个", result.size()));
        result.forEach(System.out::println);
    }

    /**
     * 通过 匿名类 实现
     * 需要接口和方法块
     */
    private static void testTwo() {

        AppleService service = new AppleService();
        List<Apple> apples = service.getApples();
        System.out.println("--------------------");
        System.out.println(String.format("苹果合计：%s 个", apples.size()));
        List<Apple> result = service.appleFilter(apples, new ApplePredicate() {
            @Override
            public boolean filter(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        System.out.println(String.format("筛选出：%s 个", result.size()));
        result.forEach(System.out::println);
    }

    /**
     * 通过 行为参数化 实现
     * 需要接口和实例类
     */
    private static void testOne() {

        AppleService service = new AppleService();
        List<Apple> apples = service.getApples();
        System.out.println("--------------------");
        System.out.println(String.format("苹果合计：%s 个", apples.size()));
        List<Apple> result = service.appleFilter(apples, new AppleRedImpl());
        System.out.println(String.format("筛选出：%s 个", result.size()));
        result.forEach(System.out::println);
    }


}
