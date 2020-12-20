package com.hamzajg.quickstart.product.webapi.endpoints.product.get;

import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;

public class GetProductByIdResponse {
    public String id;
    public String name;
    public ProductCategoryDto category;
    public float unitPrice;
    public float discount;
    public int availableQty;

    public GetProductByIdResponse(String id, String name, ProductCategoryDto category, float unitPrice, float discount, int availableQty) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.availableQty = availableQty;
    }
}
