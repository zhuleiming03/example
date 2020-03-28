package example;

import example.domain.CaloricLevel;
import example.domain.Dish;
import example.domain.Transaction;
import example.service.DishService;
import example.service.TransactionService;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CollectTest {

    @Test
    public void count() {

        List<Transaction> transactions = TransactionService.getTransaction();

        System.out.println("count：" +
                transactions.stream().collect(Collectors.counting()));
    }

    @Test
    public void listAndSetAndArrayList() {

        List<Integer> numbers = Arrays.asList(65, 32, 11, 11, 32, 53);

        numbers.stream().collect(toList()).forEach(System.out::println);
        System.out.println("--------------------");
        numbers.stream().collect(toSet()).forEach(System.out::println);
        System.out.println("--------------------");
        numbers.stream().collect(toCollection(ArrayList::new)).forEach(System.out::println);
    }

    @Test
    public void minAndMax() {

        List<Dish> dishes = DishService.getMenu();

        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> max = dishes.stream().collect(maxBy(dishComparator));
        Optional<Dish> min = dishes.stream().collect(minBy(dishComparator));

        System.out.println(String.format("max calories is %s , min calories is %s",
                max, min));
    }

    @Test
    public void summing() {

        List<Dish> dishes = DishService.getMenu();

        System.out.println("dish calories total : " +
                dishes.stream().collect(summingInt(Dish::getCalories)));
    }

    @Test
    public void averaging() {

        List<Dish> dishes = DishService.getMenu();

        System.out.println("dish calories average : " +
                dishes.stream().collect(averagingInt(Dish::getCalories)));
    }

    @Test
    public void summarizing(){

        List<Dish> dishes = DishService.getMenu();

        System.out.println("dish calories summarize : " +
                dishes.stream().collect(summarizingInt(Dish::getCalories)));
    }

    @Test
    public void joining() {

        List<Dish> dishes = DishService.getMenu();

        System.out.println("dish name : " +
                dishes.stream().map(Dish::getName).collect(Collectors.joining(" ,")));
    }

    @Test
    public void group() {

        List<Dish> menus = DishService.getMenu();

        System.out.println("---------- 按 Dish.Type 分组 ----------");
        Map<Dish.Type, List<Dish>> one = menus.stream().
                collect(groupingBy(Dish::getType));
        System.out.println(one);

        System.out.println("---------- 按 CaloricLevel 分组 ----------");
        Map<CaloricLevel, List<Dish>> two = menus.stream().
                collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println(two);

        System.out.println("---------- 按 Dish.Type,CaloricLevel 分组 ----------");
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> three = menus.stream().
                collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));
        System.out.println(three);

        System.out.println("---------- 按 Dish.Type 分组统计 ----------");
        Map<Dish.Type, Long> four = menus.stream().
                collect(groupingBy(Dish::getType, counting()));
        System.out.println(four);

        System.out.println("---------- 按 Dish.Type 分组取最大值 ----------");
        Map<Dish.Type, Optional<Dish>> five = menus.stream().
                collect(groupingBy(Dish::getType,
                        maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(five);

        System.out.println("---------- 按 Dish.Type 分组取最大值 ----------");
        Map<Dish.Type, Dish> six = menus.stream().
                collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(six);

        System.out.println("---------- 按 Dish.Type 分组取Caloric之和 ----------");
        Map<Dish.Type, Integer> seven = menus.stream().
                collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println(seven);

        System.out.println("---------- 按 Dish.Type 分组取CaloricLevel ----------");
        Map<Dish.Type, Set<CaloricLevel>> eight = menus.stream().
                collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, toSet())));
        System.out.println(eight);
    }

    @Test
    public void partition() {

        List<Dish> dishes = DishService.getMenu();

        Map<Boolean, List<Dish>> partition = dishes.stream().
                collect(partitioningBy(Dish::isVegetarian));

        List<Dish> vegetarian = partition.get(true);

        vegetarian.forEach(System.out::println);
    }

    @Test
    public void concat() {

        List<String> strings = Arrays.asList("Hello", "World", "...");

        String one = strings.stream()
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
        System.out.println(one);

        String two = strings.stream()
                .collect(() -> new StringBuilder(),
                        (l, x) -> l.append(x),
                        (r1, r2) -> r1.append(r2))
                .toString();
        System.out.println(two);

        List<Dish> dishes = DishService.getMenu();
        List<String> three = dishes.stream()
                .map(Dish::getName)
                .collect(LinkedList::new, List::add, List::addAll);
        System.out.println(three);
    }
}
