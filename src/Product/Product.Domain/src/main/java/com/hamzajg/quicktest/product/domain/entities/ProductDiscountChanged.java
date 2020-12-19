package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;

import java.util.UUID;

public class ProductDiscountChanged implements Event {
    public ProductDiscountChanged(UUID id, float newDiscount) {

    }
}
