package com.hamzajg.quicktest.order.domain.entities;

import java.util.UUID;

/**
 * One line of an order: how many of a product were bought and at what price
 * they were bought, captured when the order was placed so later price changes
 * cannot rewrite history.
 */
public class OrderItem {
    private final UUID productId;
    private final int qty;
    private final float unitPrice;

    public OrderItem(UUID productId, int qty, float unitPrice) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public UUID productId() {
        return productId;
    }

    public int qty() {
        return qty;
    }

    public float unitPrice() {
        return unitPrice;
    }

    public float lineTotal() {
        return unitPrice * qty;
    }
}
