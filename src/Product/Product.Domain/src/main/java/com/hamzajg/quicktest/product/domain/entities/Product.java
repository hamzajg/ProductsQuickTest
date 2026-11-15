package com.hamzajg.quicktest.product.domain.entities;

import com.hamzajg.quicktest.product.domain.events.*;
import com.hamzajg.quicktest.product.domain.exceptions.InsufficientStockException;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Product extends Entity {
    private String name;
    private ProductCategory category;
    private float unitPrice;
    private float discount;
    private int availableQty;

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

    public void changeName(String newName) {
        name = newName;
        addEvent(new ProductNameChanged(id, newName));
    }

    public UUID categoryId() {
        return category.id;
    }

    public void changeCategory(ProductCategory newCategory) {
        this.category = newCategory;
        addEvent(new ProductCategoryChanged(id, newCategory));
    }

    public void changeUnitPrice(float newUnitPrice) {
        this.unitPrice = newUnitPrice;
        addEvent(new ProductUnitPriceChanged(id, newUnitPrice));
    }

    public void changeDiscount(float newDiscount) {
        this.discount = newDiscount;
        addEvent(new ProductDiscountChanged(id, newDiscount));
    }

    public void changeAvailableQty(int newAvailableQty) {
        this.availableQty = newAvailableQty;
        addEvent(new ProductAvailableQtyChanged(id, newAvailableQty));
    }

    /**
     * Hold {@code qty} of this product for a checkout. Fails rather than
     * letting availability go negative, so the aggregate owns the invariant.
     */
    public void reserve(int qty) {
        if (qty <= 0)
            throw new IllegalArgumentException("Reserved quantity must be positive");
        if (qty > availableQty)
            throw new InsufficientStockException(id, qty, availableQty);
        changeAvailableQty(availableQty - qty);
    }

    /**
     * Give a previously reserved {@code qty} back to availability — used to
     * compensate a checkout that could not complete.
     */
    public void release(int qty) {
        if (qty <= 0)
            throw new IllegalArgumentException("Released quantity must be positive");
        changeAvailableQty(availableQty + qty);
    }
}
