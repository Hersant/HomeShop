package com.bse.homeshop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class ExpressDeliveryTest {

    @Test
    public void GivenRegionCityAsLocation_WhenGettingDeliveryPrice_ThenGet9e99() {
        Delivery delivery = new ExpressDelivery("Nantes");
        assertThat(delivery.getPrice()).isEqualTo(9.99, offset(0.01));
    }

    @Test
    public void GivenParisAsLocation_WhenGettingDeliveryPrice_ThenGet6e99() {
        Delivery delivery = new ExpressDelivery("Paris");
        assertThat(delivery.getPrice()).isEqualTo(6.99, offset(0.10));
    }

}
