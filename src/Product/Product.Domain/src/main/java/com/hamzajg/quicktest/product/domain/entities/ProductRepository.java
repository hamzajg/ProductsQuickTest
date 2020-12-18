package com.hamzajg.quicktest.product.domain.entities;

import java.util.Collection;
import java.util.UUID;

public interface ProductRepository {
    Product getOneById(UUID id);

    Collection<Product> getAll();

    void save(Product product);
}
