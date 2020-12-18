package com.hamzajg.quicktest.product.infrastructure.persistence;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.product.domain.entities.ProductCategoryRepository;

import java.util.Collection;

public class InMemoryProductCategoryRepository implements ProductCategoryRepository {
    @Override
    public Collection<ProductCategory> getAll() {
        return null;
    }

    @Override
    public void save(ProductCategory productCategory) {

    }
}
