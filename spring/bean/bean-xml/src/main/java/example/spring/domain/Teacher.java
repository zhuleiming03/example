package example.spring.domain;

import example.spring.service.Traffic;

public class Teacher {

    private Traffic traffic;

    public Teacher(Traffic traffic) {
        this.traffic = traffic;
    }

    public void print() {
        System.out.println("This teacher go home by " + this.traffic.method());
    }
}
