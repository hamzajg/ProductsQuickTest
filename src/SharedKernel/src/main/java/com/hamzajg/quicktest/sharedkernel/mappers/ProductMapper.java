package com.hamzajg.quicktest.sharedkernel.mappers;

import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class ProductMapper {

    public static final ProductCategoryMapper PRODUCT_CATEGORY_MAPPER = new ProductCategoryMapper();

    public ProductDto productToProductDto(Product product) {
        if (product == null)
            return null;
        var dto = new ProductDto();
        dto.id = product.id().toString();
        dto.name = product.name();
        dto.category = PRODUCT_CATEGORY_MAPPER.productCategoryToProductCategoryDto(product.category());
        dto.unitPrice = product.unitPrice();
        dto.discount = product.discount();
        dto.availableQty = product.availableQty();
        return dto;
    }

    public Collection<ProductDto> productsToProductsDto(Collection<Product> products) {
        return products.stream().map(this::productToProductDto).collect(Collectors.toList());
    }
}
