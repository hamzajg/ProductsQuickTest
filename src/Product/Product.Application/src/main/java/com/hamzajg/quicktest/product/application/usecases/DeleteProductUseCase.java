package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class DeleteProductUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public DeleteProductUseCase(UnitOfWork unitOfWork) {

        this.unitOfWork = unitOfWork;
    }

    public Product execute(UUID id) {
        return unitOfWork.productRepository().delete(id);
    }
}
