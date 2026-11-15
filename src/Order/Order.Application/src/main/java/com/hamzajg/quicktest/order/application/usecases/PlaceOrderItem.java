package com.hamzajg.quicktest.order.application.usecases;

import java.util.UUID;

/**
 * One line of a checkout request, before it has been priced.
 */
public class PlaceOrderItem {
    private final UUID productId;
    private final int qty;

    public PlaceOrderItem(UUID productId, int qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public UUID productId() {
        return productId;
    }

    public int qty() {
        return qty;
    }
}
