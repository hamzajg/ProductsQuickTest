package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.sharedkernel.dtos.ProductCategoryDto;

import java.util.Collection;

public interface ReadProductCategoryServices {
    Collection<ProductCategoryDto> getAllProductCategories();
}
