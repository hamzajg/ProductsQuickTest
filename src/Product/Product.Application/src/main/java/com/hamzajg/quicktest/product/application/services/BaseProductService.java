package com.hamzajg.quicktest.product.application.services;

import com.hamzajg.quicktest.product.application.usecases.CreateProductUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductsByCategoryUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductsUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetProductByIdUseCase;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class BaseProductService implements WriteProductService, ReadProductService {

    GetAllProductsByCategoryUseCase getAllProductsByCategoryUseCase;
    private CreateProductUseCase createProductUseCase;
    private GetAllProductsUseCase getAllProductsUseCase;
    private GetProductByIdUseCase getProductByIdUseCase;

    @Inject
    public BaseProductService(GetAllProductsByCategoryUseCase getAllProductsByCategoryUseCase, GetAllProductsUseCase getAllProductsUseCase, GetProductByIdUseCase getProductByIdUseCase) {
        this.getAllProductsByCategoryUseCase = getAllProductsByCategoryUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    public BaseProductService(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @Override
    public Collection<Product> getAllProductsByCategoryId(UUID productCategoryId) {
        return getAllProductsByCategoryUseCase.execute(productCategoryId);
    }

    @Override
    public Collection<Product> getAllProducts() {
        return getAllProductsUseCase.execute();
    }

    @Override
    public Product getOneProductById(UUID id) {
        return getProductByIdUseCase.execute(id);
    }

    @Override
    public Product createProduct(CreateProduct command) {
        return createProductUseCase.execute(command.name, UUID.fromString(command.categoryId), command.unitPrice,
                command.discount, command.availableQty);
    }
}
