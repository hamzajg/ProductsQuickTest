package com.hamzajg.quicktest.product.domain.entities;

import java.util.Collection;

public interface ProductCategoryRepository {
    Collection<ProductCategory> getAll();

    void save(ProductCategory productCategory);
}
