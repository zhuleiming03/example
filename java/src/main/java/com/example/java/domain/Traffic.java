package com.example.java.domain;

public abstract class Traffic {

    protected String type;

    protected Float pay;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPay() {
        return this.pay;
    }

    public void setPay(Float pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "{\"type\":\"" + this.type + "\",\"pay\":" + this.pay + "}";
    }

    @Override
    public Traffic clone() throws CloneNotSupportedException {
        Traffic traffic = (Traffic) super.clone();
        return traffic;
    }
}
