package example.java.reflect;

import example.java.reflect.instance.ReflectBaseAndSub;
import org.junit.Test;

/**
 * 反射基类和子类
 */
public class ReflectBaseAndSubTest {

    /**
     * 根据类名，实例化对象后执行重写方法
     */
    @Test
    public void doWorkTest() {
        ReflectBaseAndSub baseAndSub = new ReflectBaseAndSub();
        System.out.println(baseAndSub.doWorkTest("example.java.reflect.domain.Base"));
        System.out.println(baseAndSub.doWorkTest("example.java.reflect.domain.SubOne"));
        System.out.println(baseAndSub.doWorkTest("example.java.reflect.domain.SubTwo"));
    }

    /**
     * 根据方法名和入参，实例化SubTwo后执行方法
     */
    @Test
    public void SubTwoTest() {
        ReflectBaseAndSub baseAndSub = new ReflectBaseAndSub();
        System.out.println(baseAndSub.subTwoTest("getFirstMethod", "ABC"));
        System.out.println(baseAndSub.subTwoTest("getSecondMethod", "Hello"));
    }
}
