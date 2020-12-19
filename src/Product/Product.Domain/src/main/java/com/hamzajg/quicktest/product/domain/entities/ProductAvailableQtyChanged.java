package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;

import java.util.UUID;

public class ProductAvailableQtyChanged implements Event {
    public ProductAvailableQtyChanged(UUID id, int newAvailableQty) {
    }
}
