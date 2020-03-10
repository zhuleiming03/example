package example.spring.domain;

import example.spring.service.Traffic;

public class Visitor {

    private Traffic traffic;

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }

    public void print() {
        System.out.println("This visitor go home by " + this.traffic.method());
    }
}
