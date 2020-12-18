package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.application.usecases.CreateProductUseCase;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BaseProductService implements ProductService {
    @Inject
    CreateProductUseCase createProductUseCase;

    @Override
    public Product createProduct(CreateProduct command) {
        return createProductUseCase.Execute(command.name, command.categoryName, command.unitPrice,
                command.discount, command.availableQty);

    }
}
