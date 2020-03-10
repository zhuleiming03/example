package example.spring.service.impl;

import example.spring.service.Traffic;
import org.springframework.stereotype.Service;

@Service("bicycle")
public class Bicycle implements Traffic {

    public Bicycle() {
        System.out.println("-------------------- 方法一 ----------------------");
        System.out.println("class Bicycle init");
    }

    public String method() {
        return "Bicycle";
    }
}
