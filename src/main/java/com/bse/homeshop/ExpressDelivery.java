package com.bse.homeshop;

public class ExpressDelivery implements Delivery {
    private String location;

    public ExpressDelivery(String location) {
        this.location = location;
    }

    public double getPrice() {
        if (location.toLowerCase().equals("paris")) {
            return 6.99;
        } else {
            return 9.99;
        }
    }

    public String getInfo() {
        return "Livraison express en 24 heures";
    }
}
