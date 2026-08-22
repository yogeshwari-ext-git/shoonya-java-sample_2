package com.shoonya;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class BillingServiceTest {

    private final BillingService billingService = new BillingService();

    @Test
    void calculatesPredictableLineTotal() {
        BigDecimal result = billingService.calculateLineTotal(new BigDecimal("19.99"), 2);

        assertEquals(new BigDecimal("39.98"), result);
    }

    @Test
    void roundsLineTotalToTwoDecimalPlaces() {
        BigDecimal result = billingService.calculateLineTotal(new BigDecimal("10.005"), 1);

        assertEquals(new BigDecimal("10.01"), result);
    }

    @Test
    void calculatesZeroPricedLineTotal() {
        BigDecimal result = billingService.calculateLineTotal(BigDecimal.ZERO, 3);

        assertEquals(new BigDecimal("0.00"), result);
    }

    @Test
    void rejectsNonPositiveQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> billingService.calculateLineTotal(new BigDecimal("10.00"), 0));
    }

    @Test
    void rejectsNegativeUnitPrice() {
        assertThrows(IllegalArgumentException.class,
                () -> billingService.calculateLineTotal(new BigDecimal("-0.01"), 1));
    }
}
