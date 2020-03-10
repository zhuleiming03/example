package example.spring.domain;

import example.spring.service.Traffic;

public class Manager {

    private Traffic traffic;

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }

    public void print() {
        System.out.println("This manager go home by " + this.traffic.method());
    }
}
