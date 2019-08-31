package com.example.java.domain;

public class Bicycle extends Traffic {

    private Float calorie;

    public Float getCalorie() {
        return this.calorie;
    }

    public void setCalorie(Float calorie) {
        this.calorie = calorie;
    }

    @Override
    public String toString() {
        return "{\"type\":\"" + this.type + "\",\"pay\":"
                + this.pay + ",\"calorie\":" + this.calorie + "}";
    }

}
