package example.spring.service.impl;

import example.spring.service.Traffic;
import org.springframework.stereotype.Service;

@Service
public class Metro implements Traffic {

    public Metro() {
        System.out.println("-------------------- 未调用 ----------------------");
        System.out.println("class Metro init");
    }

    public String method() {
        return "Metro";
    }
}