package example;

import org.junit.Test;

public class EquelTest {

    @Test
    public void IntegerTest() {

        //a b 都取栈的常量池中值(小于128)
        Integer a = 127;
        Integer b = 127;
        System.out.println(a.equals(b));
        System.out.println(a == b);
        System.out.println(127 == a);
        System.out.println("---------------------------");

        //大于128都在堆中新建对象
        Integer aa = 128;
        Integer bb = 128;
        System.out.println(aa.equals(bb));
        System.out.println(aa == bb);
        System.out.println("---------------------------");

        //c d 都存在堆上
        Integer c = new Integer(6);
        Integer d = new Integer(6);
        System.out.println(c.equals(d));
        System.out.println(c == d);

    }

    @Test
    public void StringTest() {

        System.out.println("------------- 常量池 --------------");

        //字面量放在常量池中
        String a = "Hello";
        System.out.println("字面量定义a 和字面量是同一个引用：" + (a == "Hello"));
        String a1 = "Hello";
        System.out.println("字面量定义a 和字面量定义a1 是同一个引用：" + (a == a1));

        System.out.println("------------ 堆 ------------------");

        //new 则放在堆中
        String b = new String("Hello");
        String b1 = new String("Hello");
        String b2 = b1;
        System.out.println("new 两个字符串 在堆中是两个引用：" + (b == b1));
        System.out.println("new 字符串可以传递引用：" + (b2 == b1));
        System.out.println("new 传递后字符串引用 和 new 字符串不是同一个引用：" + (b == b2));

        System.out.println("------------ 常量池和堆 ----------");

        System.out.println("new 字符串b 和字面量定义的a 不是一个引用：" + (a == b));

        System.out.println("------------ 相加计算 ------------");

        String c = a + " world";
        String c1 = "Hello world";
        System.out.println("字面量相加不等于字面量:" + (c == c1));

        String d = b + new String(" world");
        String d1 = new String("Hello world");
        System.out.println("new 字符串相加不等于 new字符串:" + (d == d1));
    }

}