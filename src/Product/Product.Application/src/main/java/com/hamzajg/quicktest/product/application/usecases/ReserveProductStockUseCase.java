package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;

import jakarta.inject.Inject;
import java.util.UUID;

/**
 * Holds stock for a checkout. Returns {@code null} when the product does not
 * exist; propagates {@code InsufficientStockException} when it does not have
 * enough, so the caller can compensate.
 */
public class ReserveProductStockUseCase {
    private final UnitOfWork unitOfWork;

    @Inject
    public ReserveProductStockUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Product execute(UUID productId, int qty) {
        var product = unitOfWork.productRepository().getOneById(productId);
        if (product == null)
            return null;

        product.reserve(qty);
        return unitOfWork.productRepository().update(product);
    }
}
