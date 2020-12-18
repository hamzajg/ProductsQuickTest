package com.hamzajg.quickstart.product.webapi.endpoints;

public class CreateProductCategoryResponse {
    public final String id;
    public final String name;

    public CreateProductCategoryResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
