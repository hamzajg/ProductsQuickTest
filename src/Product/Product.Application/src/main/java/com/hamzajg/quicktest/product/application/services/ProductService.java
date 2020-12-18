package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

public interface ProductService {
    Product createProduct(CreateProduct command);
}
