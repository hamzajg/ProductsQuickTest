package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

public class CreateProductUseCase {
    public Product Execute(String name, String categoryName, float unitPrice, float discount, Integer availableQty) {
        return new Product(name, new ProductCategory(categoryName), unitPrice, discount, availableQty);
    }
}
