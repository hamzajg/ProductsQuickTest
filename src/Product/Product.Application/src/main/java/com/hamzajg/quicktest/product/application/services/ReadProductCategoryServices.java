package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import java.util.Collection;

public interface ReadProductCategoryServices {
    Collection<ProductCategory> getAllProductCategories();

    ProductCategory getOneProductCategoryById(String productCategoryId);
}
