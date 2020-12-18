package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.product.application.messaging.ProductCommandReqResPublisher;
import com.hamzajg.quicktest.product.infrastructure.messaging.InMemoryProductCommandReqResPublisher;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductServicesFacade {
    ProductCommandReqResPublisher productPublisherProvider = new InMemoryProductCommandReqResPublisher();
    ProductMapper productMapper = new ProductMapper();

    public ProductDto createProduct(CreateProduct command) {
        var result = productPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productMapper.productToProductDto(result);
    }
}
