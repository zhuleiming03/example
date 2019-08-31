package com.example.java.domain;

public class Wallet implements Cloneable {

    private Integer payCount;

    public Integer getPayCount() {
        return this.payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    @Override
    public String toString() {
        return "{\"Wallet\":" + this.payCount + "}";
    }

    public Wallet clone() throws CloneNotSupportedException {
        return (Wallet) super.clone();
    }
}
