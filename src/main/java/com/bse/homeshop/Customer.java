package com.bse.homeshop;

public class Customer {
    private String fullName;
    private String address;

    public Customer(String fullName, String address) {
        this.fullName = fullName;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }
}
