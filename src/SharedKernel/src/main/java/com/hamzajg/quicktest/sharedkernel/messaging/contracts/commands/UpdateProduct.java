package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public class UpdateProduct implements Command {
    public String productId;
    public String productCategoryId;
    public String name;
    public float unitPrice;
    public float discount;
    public int availableQty;

    public UpdateProduct(String productId, String productCategoryId, String name, float unitPrice, float discount, int availableQty) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.availableQty = availableQty;
    }
}
