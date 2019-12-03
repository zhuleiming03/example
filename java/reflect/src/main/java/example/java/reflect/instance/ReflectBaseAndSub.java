package example.java.reflect.instance;

import example.java.reflect.domain.Base;

import java.lang.reflect.Method;

public class ReflectBaseAndSub {

    /**
     * 根据类名，实例化对象后执行重写方法
     * @param className
     * @return
     */
    public String doWorkTest(String className) {

        try {
            Class<?> classBase = Class.forName(className);
            Base base = (Base) classBase.newInstance();
            return base.doWork();

        } catch (ClassNotFoundException e) {
            System.out.println(String.format("类：【%s】没有被发现", className));
        } catch (Exception e) {
            System.out.println("系统异常");
        }
        return null;
    }

    /**
     * 根据方法名和入参，实例化SubTwo后执行方法
     * @param methodName
     * @param methodParam
     * @return
     */
    public String subTwoTest(String methodName, String methodParam) {
        try {
            Class<?> classSubTwo = Class.forName("example.java.reflect.domain.SubTwo");
            Method methodSubTwo = classSubTwo.getDeclaredMethod(methodName, String.class);
            methodSubTwo.setAccessible(true);

            Object objectSubTwo = classSubTwo.newInstance();
            return (String) methodSubTwo.invoke(objectSubTwo, methodParam);

        } catch (Exception e) {
            System.out.println("系统异常");
        }

        return null;
    }

}
