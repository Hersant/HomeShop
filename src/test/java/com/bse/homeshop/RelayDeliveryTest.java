package com.bse.homeshop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.*;

public class RelayDeliveryTest {

    @Test
    public void GivenFreeRelay_WhenGettingDeliveryPrice_ThenGet0e() {
        Delivery delivery = new RelayDelivery(1);
        assertEquals(0.0, delivery.getPrice(), 0.01);
    }

    @Test
    public void GivenLowPriceRelay_WhenGettingDeliveryPrice_Then2e99() {
        Delivery delivery = new RelayDelivery(23);
        assertThat(delivery.getPrice()).isEqualTo(2.99, offset(0.01));
    }

    @Test
    public void GivenHighPriceRelay_WhenGettingDeliveryPrice_Then4e99() {
        Delivery delivery = new RelayDelivery(48);
        assertThat(delivery.getPrice()).isEqualTo(4.99, offset(0.01));
    }
}
