package example.spring.domain;

import example.spring.service.Traffic;

import javax.annotation.Resource;

public class Manager {

    private Traffic traffic;

    public Manager() {
        System.out.println("class Manager init ");
    }

    @Resource
    public void setTraffic(Traffic traffic) {
        System.out.println("class Manager method traffic is " + traffic);
        this.traffic = traffic;
    }

    public void print() {
        System.out.println("class Manager method print calling");
        System.out.println(">>>>>Manager go home by " + this.traffic.method());
    }
}
