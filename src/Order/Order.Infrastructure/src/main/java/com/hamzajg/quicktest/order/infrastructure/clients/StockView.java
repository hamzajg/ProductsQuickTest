package com.hamzajg.quicktest.order.infrastructure.clients;

public class StockView {
    public String productId;
    public int availableQty;

    public StockView() {
    }

    public StockView(String productId, int availableQty) {
        this.productId = productId;
        this.availableQty = availableQty;
    }
}
