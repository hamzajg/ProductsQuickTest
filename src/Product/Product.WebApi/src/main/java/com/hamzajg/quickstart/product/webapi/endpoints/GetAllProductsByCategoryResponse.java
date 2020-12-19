package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.dtos.ProductDto;

import java.util.Collection;

public class GetAllProductsByCategoryResponse {
    public Collection<ProductDto> items;

    public GetAllProductsByCategoryResponse(Collection<ProductDto> items) {
        this.items = items;
    }
}
