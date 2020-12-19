package com.hamzajg.quicktest.product.infrastructure.persistence;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.product.domain.entities.ProductCategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
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
        return productCategoryList.stream().filter(c -> c.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public ProductCategory update(ProductCategory newProductCategory) {
        var exist = productCategoryList.stream().filter(c -> c.id() == newProductCategory.id()).findFirst().orElse(null);
        if (exist == null)
            return null;
        productCategoryList.remove(exist);
        productCategoryList.add(newProductCategory);
        return newProductCategory;
    }
}
