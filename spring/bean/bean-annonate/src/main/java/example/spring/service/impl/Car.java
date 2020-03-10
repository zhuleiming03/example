package example.spring.service.impl;

import example.spring.service.Traffic;

public class Car implements Traffic {

    public Car() {
        System.out.println("class Car init");
    }

    public String method() {
        return "Car";
    }
}
