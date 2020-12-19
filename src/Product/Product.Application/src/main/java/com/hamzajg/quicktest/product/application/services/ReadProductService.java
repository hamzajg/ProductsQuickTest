package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.domain.entities.Product;

import java.util.Collection;
import java.util.UUID;

public interface ReadProductService {
    Collection<Product> getAllProductsByCategoryId(UUID productCategoryId);

    Collection<Product> getAllProducts();

    Product getOneProductById(UUID id);
}
