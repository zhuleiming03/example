package example.test;

import example.domain.Apple;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class OtherTest {

    /**
     * part 4:
     * 应用
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("------------ 苹果初始化 ------------------------");
        List<Apple> apples = initApples();
        apples.forEach(System.out::println);

        System.out.println("------------ 苹果按照重量从大到小排序 -----------");
        apples.sort(Comparator.comparing(Apple::getWeight));
        apples.forEach(System.out::println);

        System.out.println("------------ 筛选出不是绿色且小于15的苹果 -------");
        List<Apple> query = query();
        query.forEach(System.out::println);

        System.out.println("------------ 复合函数 --------------------------");
        count();
    }

    private static void count() {
        Function<Integer, Integer> a = x -> x + 1;
        Function<Integer, Integer> b = x -> x * 2;
        System.out.println("(10+1)*2= " + a.andThen(b).apply(10));
        System.out.println("10*2+1= " + a.compose(b).apply(10));
    }

    private static List<Apple> query() {
        List<Apple> apples = new LinkedList<>();
        //筛选出不是绿色且小于15
        Predicate<Apple> green = a -> a.getColor().equals("green");
        Predicate<Apple> condition = green.negate().and(a -> a.getWeight().compareTo(15f) < 0);
        for (Apple a : initApples()) {
            if (condition.test(a)) {
                apples.add(a);
            }
        }
        return apples;
    }

    private static List<Apple> initApples() {
        Map<Float, String> params = new HashMap<>();
        params.put(15.4f, "red");
        params.put(14.1f, "red");
        params.put(13.6f, "green");
        params.put(12.8f, "green");
        params.put(11.9f, "pink");
        return getApples(params, Apple::new);
    }

    private static List<Apple> getApples(Map<Float,String> params, BiFunction<Float,String,Apple> function) {
        List<Apple> apples = new LinkedList<>();
        for (Map.Entry<Float, String> entry : params.entrySet()) {
            Apple apple = function.apply(entry.getKey(), entry.getValue());
            apples.add(apple);
        }
        return apples;
    }

}
