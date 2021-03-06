package com.hamzajg.quickstart.product.webapi.endpoints.product.category.get;

import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;

import java.util.Collection;

public class GetAllProductCategoriesResponse {
    public Collection<ProductCategoryDto> items;

    public GetAllProductCategoriesResponse(Collection<ProductCategoryDto> items) {
        this.items = items;
    }
}
