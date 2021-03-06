package com.hamzajg.quicktest.product.application.usecases

import com.hamzajg.quicktest.product.application.UnitOfWork
import spock.lang.Specification
import spock.lang.Unroll

class CreateProductUseCaseTest extends Specification {

    @Unroll
    def 'Can Create New Product'() {
        given:
        def unitOfWork = new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository())
        when:
        def category = new CreateProductCategoryUseCase(unitOfWork).execute(ProductCategoryName)
        new CreateProductUseCase(unitOfWork).execute(ProductName, category.id(), ProductUnitPrice,
                ProductDiscount, ProductAvailableQty)
        then:
        def product = unitOfWork.productRepository().getAll()[0]
        product.id != null
        product.unitPrice == ProductUnitPrice
        product.name == ProductName
        product.categoryName() == ProductCategoryName
        product.discount == ProductDiscount
        product.availableQty == ProductAvailableQty
        where:
        ProductName    | ProductCategoryName     | ProductUnitPrice | ProductDiscount | ProductAvailableQty
        "Test Product" | "Test Product Category" | 55.5f            | 15.5f           | 200
    }
}
