package com.hamzajg.quickstart.product.webapi.endpoints.product.category.get;

public class GetProductCategoryByIdResponse {
    public final String id;
    public final String name;

    public GetProductCategoryByIdResponse(String id, String name) {

        this.id = id;
        this.name = name;
    }
}
