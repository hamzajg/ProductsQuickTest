package com.hamzajg.quicktest.product.application.usecases;

import com.hamzajg.quicktest.product.application.UnitOfWork;
import com.hamzajg.quicktest.product.domain.entities.Product;

import javax.inject.Inject;
import java.util.UUID;

public class UpdateProductUseCase {
    private UnitOfWork unitOfWork;

    @Inject
    public UpdateProductUseCase(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Product execute(UUID productId, UUID productCategoryId, String name, float unitPrice, float discount, int availableQty) {
        var result = unitOfWork.productRepository().getOneById(productId);
        if (result == null)
            return null;

        if (!result.name().equals(name))
            result.changeName(name);
        if (!result.categoryId().equals(productCategoryId)) {
            var newCategory = unitOfWork.productCategoryRepository().getOneById(productCategoryId);
            if(newCategory == null)
                return null;
            result.changeCategory(newCategory);
        }
        if (result.unitPrice() != unitPrice)
            result.changeUnitPrice(unitPrice);
        if (result.discount()!= discount)
            result.changeDiscount(discount);
        if (result.availableQty()!= availableQty)
            result.changeAvailableQty(availableQty);

        return unitOfWork.productRepository().update(result);
    }
}
