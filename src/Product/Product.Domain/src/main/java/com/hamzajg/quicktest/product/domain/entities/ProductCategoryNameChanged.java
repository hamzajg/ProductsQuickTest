package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;

import java.util.UUID;

public class ProductCategoryNameChanged implements Event {
    private UUID id;
    private UUID productCategoryId;
    private String newName;

    public ProductCategoryNameChanged(UUID id, String newName) {
        this.id = UUID.randomUUID();
        productCategoryId = id;
        this.newName = newName;
    }
}
