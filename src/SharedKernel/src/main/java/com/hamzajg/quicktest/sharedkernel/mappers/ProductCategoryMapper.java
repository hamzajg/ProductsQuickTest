package com.hamzajg.quicktest.sharedkernel.mappers;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;

public class ProductCategoryMapper {
    public ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory) {
        if (productCategory == null)
            return null;
        var dto = new ProductCategoryDto();
        dto.id = productCategory.id().toString();
        dto.name = productCategory.name();
        return dto;
    }
}
