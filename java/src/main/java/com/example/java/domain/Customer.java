package com.example.java.domain;

public class Customer {

    public Customer(String customerID) {
        this.customerID = customerID;
    }

    private String customerID;

    private Traffic traffic;

    public String getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Traffic getTraffic() {
        return this.traffic;
    }

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }

    @Override
    public String toString() {
        return "{\"CustomerID\":\"" + this.customerID + "\",\"Traffic\":" + this.traffic + "}";
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        Customer customer = (Customer) super.clone();
        customer.setTraffic(this.traffic.clone());
        return customer;
    }

}
