package com.example.java.domain;

public class Customer implements Cloneable {

    public Customer(String customerID) {
        this.customerID = customerID;
    }

    private String customerID;

    private Traffic traffic;

    private Wallet wallet;

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

    public Wallet getWallet(){
        return this.wallet;
    }

    public void setWallet(Wallet wallet){
        this.wallet=wallet;
    }

    @Override
    public String toString() {
        return "{\"CustomerID\":\"" + this.customerID
                + "\",\"Wallet:\": " + this.wallet
                + ",\"Traffic\":" + this.traffic + "}";
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        Customer customer = (Customer) super.clone();
        customer.wallet = this.wallet.clone();
        return customer;
    }

}
