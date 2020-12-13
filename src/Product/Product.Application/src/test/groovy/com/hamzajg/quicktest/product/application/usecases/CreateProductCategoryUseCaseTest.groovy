package com.hamzajg.quicktest.product.application.usecases

import com.hamzajg.quicktest.product.application.UnitOfWork
import spock.lang.Specification
import spock.lang.Unroll

class CreateProductCategoryUseCaseTest extends Specification {

    @Unroll
    def 'Can Create New Product Category'() {
        given:
        def unitOfWork = new UnitOfWork(new InMemoryProductCategoryRepository(), new InMemoryProductRepository())
        when:
        new CreateProductCategoryUseCase(unitOfWork).Execute(ProductCategoryName)
        then:
        def productCategory = unitOfWork.productCategoryRepository().getAll()[0]
        productCategory.id != null
        productCategory.name == ProductCategoryName
        where:
        ProductCategoryName     | placeholder
        "Test Product Category" | null

    }

}
