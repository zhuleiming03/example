package example.service.impl;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionService {

    public static <T> boolean check(T t,Predicate<T> predicate) {
        return predicate.test(t);
    }

    public static boolean checkInt(int number, IntPredicate intPredicate) {
        return intPredicate.test(number);
    }

    public static <T> T create(Supplier<T> supplier) {
        return supplier.get();
    }

    public static <T> void print(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }

    public static <T, R> Map<T, R> map(List<T> list, Function<T, R> function) {
        Map<T, R> result = new HashMap<>();
        for (T t : list) {
            result.put(t, function.apply(t));
        }
        return result;
    }

    public static int math(int a,int b, IntBinaryOperator intBinaryOperator) {
        return intBinaryOperator.applyAsInt(a, b);
    }

    public static <T> int compare(T left, T right, Comparator<T> comparator) {
        return comparator.compare(left, right);
    }
}
