package com.hamzajg.quicktest.product.domain.events;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import java.util.UUID;

public class ProductCreated implements Event {
    private final UUID id;
    private final UUID productId;
    private final String productName;
    private final ProductCategory productCategory;
    private final float productUnitPrice;
    private final float productDiscount;
    private final int productAvailableQty;

    public ProductCreated(UUID productId, String productName, ProductCategory productCategory, float productUnitPrice, float productDiscount, int productAvailableQty) {
        id = UUID.randomUUID();
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productUnitPrice = productUnitPrice;
        this.productDiscount = productDiscount;
        this.productAvailableQty = productAvailableQty;
    }
}
