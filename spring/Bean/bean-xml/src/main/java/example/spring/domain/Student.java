package example.spring.domain;

import example.spring.service.Traffic;

public class Student {

    private Traffic traffic;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }

    public void print() {
        System.out.println("This student's name is " + this.name);
        System.out.println("He go home by " + this.traffic.method());
    }
}
