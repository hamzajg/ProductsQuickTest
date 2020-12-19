package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;

import javax.inject.Inject;
import java.util.UUID;

public class GetProductByIdUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public GetProductByIdUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Product execute(UUID productId) {
        return unitOfWork.productRepository().getOneById(productId);
    }
}
