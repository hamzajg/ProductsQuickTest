package com.hamzajg.quicktest.product.infrastructure.persistence;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.product.domain.entities.ProductCategoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class InMemoryProductCategoryRepository implements ProductCategoryRepository {
    private static final List<ProductCategory> productCategoryList = new ArrayList<>();

    @Override
    public Collection<ProductCategory> getAll() {
        return productCategoryList;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        productCategoryList.add(productCategory);
        return productCategory;
    }

    @Override
    public ProductCategory getOneById(UUID id) {
        return productCategoryList.stream().filter(c -> c.id() == id).findFirst().orElse(null);
    }
}
