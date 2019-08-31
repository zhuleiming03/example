package com.example.java;

import com.example.java.implement.CacheServiceImpl;
import com.example.java.service.CacheService;
import org.junit.Test;

public class OopTests {

    /**
     * 可变参数方法调用
     * 过时方法注释
     */
    @Test
    public void variableArgumentsTest() {
        CacheService cacheService = new CacheServiceImpl();
        System.out.println("累计合计：" + cacheService.getCacheQueryCount("add", 1, 2, 3, 4));
        System.out.println("累计合计：" + cacheService.getAddUpResult(1, 2, 3, 4, 5));
    }

    /**
     * Object Equals 写法规范
     */
    @Test
    public void objectEqualsTest() {
        Object object = "test";
        if (object.equals("test")) {
            System.out.println("object is test");
        } else {
            System.out.println("object not is test");
        }

        if ("test".equals(object)) {
            System.out.println("test is object");
        } else {
            System.out.println("test not is object");
        }

        object = null;
        try {
            if (object.equals("test")) {
                System.out.println("object is test");
            } else {
                System.out.println("object not is test");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            if ("test".equals(object)) {
                System.out.println("test is object");
            } else {
                System.out.println("test not is object");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Integer Equals 测试
     * 值在-128至127 时可以用= 超出该范围只能用 Equals
     */
    @Test
    public void integerEqualsTest() {
        Integer a1 = 15;
        Integer a2 = Integer.valueOf(15);
        Integer b1 = 128;
        Integer b2 = Integer.valueOf(128);

        if (a1 == a2) {
            System.out.println("Integer 15 == Integer 15 值保存在 IntegerCache.cache -128至127");
        } else {
            System.out.println("Integer 15 != Integer 15");
        }

        if (b1 == b2) {
            System.out.println("Integer 128 == Integer 128 ");
        } else {
            System.out.println("Integer 128 != Integer 128 对象在堆中");
        }

        if (b1.equals(b2) ) {
            System.out.println("Integer 128 equals Integer 128 对象在堆中 ");
        } else {
            System.out.println("Integer 128 != Integer 128 ");
        }
    }

    /**
     * String Split 出的数组会自动过滤空格
     */
    @Test
    public void stringSplit() {
        String stringArry = "a,b,c,,,";
        String[] ary = stringArry.split(",");
        System.out.println("预期长度6，实际长度：" + ary.length);
    }
}
