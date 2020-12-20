package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.Event;
import com.hamzajg.quicktest.product.domain.events.ProductCategoryCreated;
import com.hamzajg.quicktest.product.domain.events.ProductCategoryNameChanged;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class ProductCategory extends Entity {
    private String name;
    public Collection<Event> events = Collections.unmodifiableCollection(eventCollection);

    public ProductCategory(String name) {
        this.name = name;
        addEvent(new ProductCategoryCreated(id, name));
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return this.name;
    }

    public void changeName(String newName) {
        this.name = newName;
        addEvent(new ProductCategoryNameChanged(id, name));
    }
}
