package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;
import com.hamzajg.quicktest.product.domain.events.ProductCreated;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Product extends Entity {
    private final String name;
    private final ProductCategory category;
    private final float unitPrice;
    private final float discount;
    private final int availableQty;

    public String categoryName() {
        return category.name();
    }

    public Collection<Event> events = Collections.unmodifiableCollection(eventCollection);

    public Product(String name, ProductCategory category, float unitPrice, float discount, int availableQty) {
        super();
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.availableQty = availableQty;

        addEvent(new ProductCreated(id, name, category, unitPrice, discount, availableQty));
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public ProductCategory category() {
        return category;
    }

    public float unitPrice() {
        return unitPrice;
    }

    public float discount() {
        return discount;
    }

    public int availableQty() {
        return availableQty;
    }
}
