package com.bse.homeshop;

public class RelayDelivery implements Delivery {

    private int nbRelay;

    public RelayDelivery(int nbRelay) {
        this.nbRelay = nbRelay;
    }

    public double getPrice() {
        if (nbRelay >= 1 && nbRelay <= 22) {
            return 0;
        } else if (nbRelay >= 23 && nbRelay <= 47) {
            return 2.99;
        }
        return 4.99;
    }
}
