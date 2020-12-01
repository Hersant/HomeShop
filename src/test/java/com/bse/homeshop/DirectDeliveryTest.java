package com.bse.homeshop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectDeliveryTest {
    @Test
    public void GivenDirectDelivery_WhenGettingPrice_ThenGet4e99() {
        Delivery delivery = new DirectDelivery();
        assertThat(delivery.getPrice()).isEqualTo(4.99);
    }
}
