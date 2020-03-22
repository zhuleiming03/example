package example.service.impl;

import example.domain.Apple;
import example.service.ApplePredicate;
import example.service.Predicate;

import java.util.LinkedList;
import java.util.List;

public class AppleService {

    /**
     * 遍历Apple数组
     *
     * @param apples
     * @param method
     * @return
     */
    public List<Apple> appleFilter(List<Apple> apples, ApplePredicate method) {
        List<Apple> result = new LinkedList<>();
        for (Apple apple : apples) {
            if (method.filter(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 遍历数组
     *
     * @param
     * @param method
     * @return
     */
    public <T> List<T> filter(List<T> list, Predicate<T> method) {
        List<T> result = new LinkedList<>();
        for (T t : list) {
            if (method.filter(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 获取初始数据
     *
     * @return
     */
    public List<Apple> getApples() {

        List<Apple> apples = new LinkedList<>();

        apples.add(new Apple(12.4f, "red"));
        apples.add(new Apple(11.6f, "pink"));
        apples.add(new Apple(13.2f, "green"));
        apples.add(new Apple(15.1f, "red"));

        return apples;
    }
}
