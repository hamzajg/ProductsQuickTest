package com.hamzajg.quicktest.product.domain.entities;

import java.util.Collection;
import java.util.UUID;

public interface ProductCategoryRepository {
    Collection<ProductCategory> getAll();

    ProductCategory save(ProductCategory productCategory);

    ProductCategory getOneById(UUID id);

    ProductCategory update(ProductCategory newProductCategory);

    ProductCategory delete(UUID id);
}
