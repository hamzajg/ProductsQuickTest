package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

import java.util.Collection;
import java.util.UUID;

public interface WriteProductService {
    Product createProduct(CreateProduct command);
}

