package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.application.usecases.CreateProductUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductsByCategoryUseCase;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class BaseProductService implements WriteProductService, ReadProductService {
    @Inject
    CreateProductUseCase createProductUseCase;
    @Inject
    GetAllProductsByCategoryUseCase getAllProductsByCategoryUseCase;

    @Override
    public Product createProduct(CreateProduct command) {
        return createProductUseCase.execute(command.name, command.categoryName, command.unitPrice,
                command.discount, command.availableQty);
    }

    @Override
    public Collection<Product> getAllProductsByCategoryId(UUID productCategoryId) {
        return getAllProductsByCategoryUseCase.execute(productCategoryId);
    }
}
