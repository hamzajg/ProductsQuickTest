package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;

import jakarta.inject.Inject;
import java.util.UUID;

/**
 * Compensates a {@link ReserveProductStockUseCase}: gives held stock back.
 * Returns {@code null} when the product does not exist.
 */
public class ReleaseProductStockUseCase {
    private final UnitOfWork unitOfWork;

    @Inject
    public ReleaseProductStockUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Product execute(UUID productId, int qty) {
        var product = unitOfWork.productRepository().getOneById(productId);
        if (product == null)
            return null;

        product.release(qty);
        return unitOfWork.productRepository().update(product);
    }
}
