package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class CreateProductUseCase {

    private UnitOfWork unitOfWork;

    @Inject
    public CreateProductUseCase() {

    }

    public CreateProductUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Product execute(String name, UUID categoryId, float unitPrice, float discount, Integer availableQty) {
        var product = new Product(name, unitOfWork.productCategoryRepository().getOneById(categoryId), unitPrice, discount, availableQty);
        return unitOfWork.productRepository().save(product);
    }
}
