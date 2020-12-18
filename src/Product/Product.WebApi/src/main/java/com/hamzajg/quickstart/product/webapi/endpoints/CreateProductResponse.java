package com.hamzajg.quickstart.product.webapi.endpoints;

public class CreateProductResponse {
    public String id;
    public String name;
    public String categoryName;
    public float unitPrice;
    public float discount;
    public int availableQty;

    public CreateProductResponse(String id, String name, String categoryName, float unitPrice, float discount, int availableQty) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.availableQty = availableQty;
    }
}
