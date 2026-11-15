package com.hamzajg.quickstart.product.webapi.endpoints.product.stock;

public class StockChangeResponse {
    public String productId;
    public int availableQty;

    public StockChangeResponse(String productId, int availableQty) {
        this.productId = productId;
        this.availableQty = availableQty;
    }
}
