package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;
import com.hamzajg.quicktest.product.domain.events.ProductCategoryCreated;

import java.util.Collection;
import java.util.Collections;

public class ProductCategory extends Entity {
    private String name;
    public Collection<Event> events = Collections.unmodifiableCollection(eventCollection);

    public ProductCategory(String name) {
        this.name = name;
        addEvent(new ProductCategoryCreated(id, name));
    }

    public String name() {
        return this.name;
    }
}
