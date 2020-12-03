package com.bse.homeshop;

public class TakeAwayDelivery implements Delivery {
    public double getPrice() {
        return 0;
    }
    public String getInfo() {
        return "Livraison directe.";
    }
}
