package com.hamzajg.quicktest.product.domain.entities;

public class ProductCategory {
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }
}
