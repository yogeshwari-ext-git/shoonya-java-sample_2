package com.shoonya;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.Validate;

/**
 * Performs the small, deterministic billing operation used by this POC.
 */
public final class BillingService {

    /**
     * Calculates a line total and returns a currency-style value with two decimal places.
     *
     * @param unitPrice price of one item; must be zero or greater
     * @param quantity number of items; must be greater than zero
     * @return unit price multiplied by quantity, rounded to two decimal places
     */
    public BigDecimal calculateLineTotal(BigDecimal unitPrice, int quantity) {
        Validate.notNull(unitPrice, "Unit price must not be null");
        Validate.isTrue(unitPrice.signum() >= 0, "Unit price must not be negative");
        Validate.isTrue(quantity > 0, "Quantity must be greater than zero");

        return unitPrice.multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        BillingService service = new BillingService();
        BigDecimal total = service.calculateLineTotal(new BigDecimal("19.99"), 2);
        System.out.println("Billing total: " + total);
    }
}
