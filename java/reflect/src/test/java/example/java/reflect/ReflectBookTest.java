package example.java.reflect;

import example.java.reflect.instance.ReflectBook;
import org.junit.Test;

/**
 * 反射一个对象
 */
public class ReflectBookTest {

    @Test
    public void contextLoads() {

        try {

            // 创建对象
            ReflectBook.reflectNewInstance();

            // 反射私有的构造方法
            ReflectBook.reflectPrivateConstructor();

            // 反射私有属性
            ReflectBook.reflectPrivateField();

            // 反射私有方法
            ReflectBook.reflectPrivateMethod();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
