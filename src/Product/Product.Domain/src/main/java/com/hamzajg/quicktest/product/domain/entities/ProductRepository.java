package com.hamzajg.quicktest.product.domain.entities;

import java.util.Collection;
import java.util.UUID;

public interface ProductRepository {
    Product getOneById(UUID id);

    Collection<Product> getAll();

    Product save(Product product);

    Collection<Product> getAllByCategoryId(UUID productCategoryId);

    Product update(Product newProduct);
}
