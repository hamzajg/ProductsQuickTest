package com.hamzajg.quicktest.sharedkernel.mappers;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class ProductCategoryMapper {
    public ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory) {
        if (productCategory == null)
            return null;
        var dto = new ProductCategoryDto();
        dto.id = productCategory.id().toString();
        dto.name = productCategory.name();
        return dto;
    }

    public Collection<ProductCategoryDto> productCategoriesToProductCategoriesDto(Collection<ProductCategory> productCategories) {
        return productCategories.stream().map(this::productCategoryToProductCategoryDto).collect(Collectors.toList());
    }
}
