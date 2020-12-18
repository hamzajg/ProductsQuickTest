package com.hamzajg.quicktest.sharedkernel.mappers;

import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductDto;

public class ProductMapper {
    public ProductDto productToProductDto(Product product) {
        if(product == null)
            return null;
        var dto = new ProductDto();
        dto.id = product.id().toString();
        dto.name = product.name();
        dto.categoryName = product.categoryName();
        dto.unitPrice = product.unitPrice();
        dto.discount = product.discount();
        dto.availableQty = product.availableQty();
        return dto;
    }
}
