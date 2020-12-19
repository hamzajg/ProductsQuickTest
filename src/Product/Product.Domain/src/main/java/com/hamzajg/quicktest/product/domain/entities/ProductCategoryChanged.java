package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;

import java.util.UUID;

public class ProductCategoryChanged implements Event {
    private final UUID id;
    private final UUID productId;
    private final ProductCategory newCategory;

    public ProductCategoryChanged(UUID id, ProductCategory newCategory) {
        this.id = UUID.randomUUID();
        productId = id;
        this.newCategory = newCategory;
    }
}
