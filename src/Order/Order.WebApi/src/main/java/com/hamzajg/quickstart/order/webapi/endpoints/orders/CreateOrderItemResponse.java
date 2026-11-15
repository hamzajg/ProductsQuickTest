package com.hamzajg.quickstart.order.webapi.endpoints.orders;

public class CreateOrderItemResponse {
    public String productId;
    public int qty;
    public float unitPrice;

    public CreateOrderItemResponse(String productId, int qty, float unitPrice) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}
