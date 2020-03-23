package example;

import example.domain.Dish;
import example.domain.Transaction;
import example.service.DishService;
import example.service.TransactionService;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BaseTest {

    @Test
    public void forEach() {

        System.out.println("------------- forEach(终端) ------------");

        List<String> strings = Arrays.asList("Hello", "World");

        strings.forEach(System.out::println);
    }

    @Test
    public void filter() {

        List<Integer> numbers = Arrays.asList(3, 4, 4, 2, 3, 4, 5, 5, 23, 4, 2);

        System.out.println("------------- filter(中间) ------------");

        numbers.stream().filter(i -> i.equals(4)).forEach(System.out::println);
    }

    @Test
    public void distinct() {

        List<Integer> numbers = Arrays.asList(3, 4, 4, 2, 3, 4, 5, 5, 23, 4, 2);

        System.out.println("------------- distinct(中间) ------------");

        numbers.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void limit() {

        List<Integer> numbers = Arrays.asList(3, 4, 4, 2, 3, 4, 5, 5, 23, 4, 2);

        System.out.println("------------- limit(中间) ------------");

        numbers.stream().limit(3).forEach(System.out::println);
    }

    @Test
    public void skip() {

        List<Integer> numbers = Arrays.asList(3, 4, 4, 2, 3, 4, 5, 5, 23, 4, 2);

        System.out.println("------------- skip(中间) ------------");

        numbers.stream().skip(2).limit(3).forEach(System.out::println);
    }

    @Test
    public void map() {

        List<Dish> meun = DishService.getMenu();

        System.out.println("------------- map(中间) ------------");

        meun.stream().map(Dish::getName).forEach(System.out::println);
    }

    @Test
    public void flatMap() {

        List<Dish> menu = DishService.getMenu();

        System.out.println("------------- flatMap(中间) ------------");

        Stream<String> words = menu.stream().map(Dish::getName);
        Stream<String[]> letters = words.map(l -> l.split(""));
        Stream<String> result = letters.flatMap(Arrays::stream).distinct();

        result.forEach(System.out::println);
    }

    @Test
    public void sorted() {

        List<Transaction> transactions = TransactionService.getTransaction();

        System.out.println("------------- sorted(中间) ------------");

        transactions.stream().sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }

    @Test
    public void anyMatch() {

        List<Dish> meun = DishService.getMenu();

        System.out.println("------------- anyMatch(终端) ------------");

        if (meun.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("exist vegetarian dish");
        }
    }

    @Test
    public void allMatch() {

        List<Dish> meun = DishService.getMenu();

        System.out.println("------------- allMatch(终端) ------------");

        if (meun.stream().allMatch(dish -> dish.getCalories() > 100)) {
            System.out.println("all dish's calories bigger than 100");
        }
    }

    @Test
    public void noneMatch() {

        List<Dish> meun = DishService.getMenu();

        System.out.println("------------- noneMatch(终端) ------------");

        if (meun.stream().noneMatch(dish -> dish.getCalories() > 1000)) {
            System.out.println("none dish's calories bigger than 1000");
        }
    }

    @Test
    public void findFirst() {

        List<Dish> meun = DishService.getMenu();

        System.out.println("------------- findFirst(终端) ------------");

        Optional<Dish> dish = meun.stream().filter(Dish::isVegetarian).findFirst();
        if (dish.isPresent()) {
            System.out.println("First vegetarian dish is " + dish);
        }
    }

    @Test
    public void findAny() {

        List<Dish> meun = DishService.getMenu();

        System.out.println("------------- findAny(终端) ------------");

        meun.stream().filter(Dish::isVegetarian).findAny()
                .ifPresent(s -> System.out.println("exist vegetarian dish is " + s.getName()));
    }

    @Test
    public void reduce() {

        System.out.println("------------- reduce(终端) ------------");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Integer add = numbers.stream().reduce(6, (x, y) -> x + y);
        System.out.println("数组之和是：" + add);

        int addNum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("数组之和(非拆箱写法)是：" + addNum);

        Integer mult = numbers.stream().reduce(1, (x, y) -> x * y);
        System.out.println("数组之积是：" + mult);

        numbers.stream().reduce(Integer::min)
                .ifPresent(n -> System.out.println("数组最小值是：" + n));

        numbers.stream().reduce(Integer::max)
                .ifPresent(n -> System.out.println("数组最大值是：" + n));
    }

    @Test
    public void count() {

        System.out.println("------------- count(终端) ------------");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("number's count is " + numbers.stream().count());
    }
}
