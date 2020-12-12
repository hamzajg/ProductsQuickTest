package com.hamzajg.quicktest.product.application.usecases

import spock.lang.Specification
import spock.lang.Unroll

class CreateProductUseCaseTest extends Specification {

    @Unroll
    def 'Can Create New Product'() {
        when:
        def product = new CreateProductUseCase().Execute(ProductName, ProductCategoryName, ProductUnitPrice, ProductDiscount, ProductAvailableQty)
        then:
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
