package com.hamzajg.quicktest.product.domain.entities;

import java.util.Collection;

public interface ProductRepository {
    Collection<Product> getAll();

    void save(Product product);
}
