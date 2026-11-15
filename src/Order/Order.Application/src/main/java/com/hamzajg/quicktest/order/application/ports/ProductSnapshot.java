package com.hamzajg.quicktest.order.application.ports;

/**
 * A priced snapshot of a product, taken from the products service when its
 * stock is held. The price is captured here so the order can be priced without
 * a second lookup.
 */
public class ProductSnapshot {
    private final String productId;
    private final float unitPrice;
    private final int availableQty;

    public ProductSnapshot(String productId, float unitPrice, int availableQty) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.availableQty = availableQty;
    }

    public String productId() {
        return productId;
    }

    public float unitPrice() {
        return unitPrice;
    }

    public int availableQty() {
        return availableQty;
    }
}
