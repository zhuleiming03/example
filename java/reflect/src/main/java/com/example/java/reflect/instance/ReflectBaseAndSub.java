package com.example.java.reflect.instance;

import com.example.java.reflect.domain.Base;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Slf4j
@Component
public class ReflectBaseAndSub {

    public String doWorkTest(String className) {

        try {
            Class<?> classBase= Class.forName(className);
            Base base = (Base) classBase.newInstance();
            return base.doWork();

        } catch (ClassNotFoundException e) {
            log.error(String.format("类：【%s】没有被发现", className), e);
        } catch (Exception e) {
            log.error("系统异常", e);
        }
        return null;
    }

    public String subTwoTest(String methodName,String methodParam) {
        try {
            Class<?> classSubTwo = Class.forName("com.example.java.reflect.domain.SubTwo");
            Method methodSubTwo = classSubTwo.getDeclaredMethod(methodName, String.class);
            methodSubTwo.setAccessible(true);

            Object objectSubTwo = classSubTwo.newInstance();
            return (String) methodSubTwo.invoke(objectSubTwo, methodParam);

        } catch (Exception e) {
            log.error("系统异常", e);
        }

        return null;
    }

}
