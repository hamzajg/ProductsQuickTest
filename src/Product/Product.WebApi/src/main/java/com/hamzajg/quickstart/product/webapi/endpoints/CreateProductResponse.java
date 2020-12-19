package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;

public class CreateProductResponse {
    public String id;
    public String name;
    public ProductCategoryDto category;
    public float unitPrice;
    public float discount;
    public int availableQty;

    public CreateProductResponse(String id, String name, ProductCategoryDto category, float unitPrice, float discount, int availableQty) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.availableQty = availableQty;
    }
}
