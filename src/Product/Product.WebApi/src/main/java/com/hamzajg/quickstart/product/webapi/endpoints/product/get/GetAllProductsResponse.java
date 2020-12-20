package com.hamzajg.quickstart.product.webapi.endpoints.product.get;

import com.hamzajg.quicktest.sharedkernel.dtos.ProductDto;

import java.util.Collection;

public class GetAllProductsResponse {
    public Collection<ProductDto> items;

    public GetAllProductsResponse(Collection<ProductDto> items) {

        this.items = items;
    }
}
