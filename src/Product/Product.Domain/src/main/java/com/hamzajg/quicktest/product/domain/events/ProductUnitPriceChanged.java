package com.hamzajg.quicktest.product.domain.events;

import com.hamzajg.quicktest.product.domain.events.Event;

import java.util.UUID;

public class ProductUnitPriceChanged implements Event {
    public ProductUnitPriceChanged(UUID id, float newUnitPrice) {
    }
}
