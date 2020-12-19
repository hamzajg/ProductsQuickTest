package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;

import java.util.UUID;

public class ProductNameChanged implements Event {
    private final UUID id;
    private final UUID productId;
    private final String newName;

    public ProductNameChanged(UUID id, String newName) {
        this.id = UUID.randomUUID();
        productId = id;
        this.newName = newName;
    }
}
