package com.hamzajg.quickstart.product.webapi.endpoints.product;

import com.hamzajg.quicktest.product.application.messaging.ProductCommandReqResPublisher;
import com.hamzajg.quicktest.product.application.services.BaseProductService;
import com.hamzajg.quicktest.product.application.services.ReadProductService;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductsByCategoryUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetAllProductsUseCase;
import com.hamzajg.quicktest.product.application.usecases.GetProductByIdUseCase;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.InMemoryProductCommandReqResPublisher;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.category.create.CreateProductCategoryHandler;
import com.hamzajg.quicktest.sharedkernel.dtos.ProductDto;
import com.hamzajg.quicktest.sharedkernel.mappers.ProductMapper;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProduct;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class ProductServicesFacade {
    private ReadProductService baseProductService = new BaseProductService(new GetAllProductsByCategoryUseCase(CreateProductCategoryHandler.unitOfWork),
            new GetAllProductsUseCase(CreateProductCategoryHandler.unitOfWork), new GetProductByIdUseCase(CreateProductCategoryHandler.unitOfWork));
    private ProductCommandReqResPublisher productPublisherProvider = new InMemoryProductCommandReqResPublisher();
    private ProductMapper productMapper = new ProductMapper();

    public ProductDto createProduct(CreateProduct command) {
        var result = productPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productMapper.productToProductDto(result);
    }

    public Collection<ProductDto> getAllProductsByCategoryId(String productCategoryId) {
        var result = baseProductService.getAllProductsByCategoryId(UUID.fromString(productCategoryId));
        if (result == null)
            return null;
        return productMapper.productsToProductsDto(result);
    }

    public Collection<ProductDto> getAllProducts() {
        var result = baseProductService.getAllProducts();
        if (result == null)
            return null;
        return productMapper.productsToProductsDto(result);
    }

    public ProductDto getOneProductById(String productId) {
        var result = baseProductService.getOneProductById(UUID.fromString(productId));
        if (result == null)
            return null;
        return productMapper.productToProductDto(result);
    }

    public ProductDto updateProduct(UpdateProduct command) {
        var result = productPublisherProvider.publishAndWait(command);
        if (result == null)
            return null;
        return productMapper.productToProductDto(result);
    }

    public String deleteProduct(String productId) {
        var result = productPublisherProvider.publishAndWait(new DeleteProduct(productId));
        if (result == null)
            return null;
        return result.id().toString();
    }
}
