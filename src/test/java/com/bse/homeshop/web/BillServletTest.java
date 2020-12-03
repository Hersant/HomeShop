package com.bse.homeshop.web;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillServletTest {
    @Test
    public void GivenTwoParams_WhenSplitParameters_ThenFillMapCorrectly() {
        BillServlet billServlet = new BillServlet();
        Map<String, String> params = billServlet.splitParameters("k1=v1&k2=v2");
        assertThat(params.size()).isEqualTo(2);
        assertThat(params.get("k1")).isEqualTo("v1");
        assertThat(params.get("k2")).isEqualTo("v2");
    }

    @Test
    public void GivenOneGoodParamAndOneBadParam_WhenSplitParameters_ThenFillMapCorrectly() {
        BillServlet billServlet = new BillServlet();
        Map<String, String> params = billServlet.splitParameters("k1=v1&k2");
        assertEquals(1, params.size());
        assertEquals("v1", params.get("k1"));
    }

    @Test
    public void GivenBadParams_WhenSplitParameters_ThenDoNotFillMap() {
        BillServlet billServlet = new BillServlet();
        Map<String, String> params = billServlet.splitParameters("Bad params");
        assertEquals(0, params.size());
    }


}
