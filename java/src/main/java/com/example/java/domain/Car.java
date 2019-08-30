package com.example.java.domain;

public class Car extends Traffic {

    private String plateNumber;

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        return "{\"type\":\"" + this.type + "\",\"pay\":"
                + this.pay + ",\"plateNumber\":\"" + this.plateNumber + "\"}";
    }
}
