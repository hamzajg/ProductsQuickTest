package com.hamzajg.quicktest.product.infrastructure.messaging.product;

import com.hamzajg.quicktest.product.application.messaging.ProductCommandReqResPublisher;
import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.category.create.CreateProductCategoryHandler;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.create.CreateProductCommandSubscribable;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.create.CreateProductEventSubscribable;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.create.CreateProductHandler;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.update.UpdateProductCommandSubscribable;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.update.UpdateProductEventSubscribable;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.update.UpdateProductHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.CreateProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.UpdateProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InMemoryProductCommandReqResPublisher implements ProductCommandReqResPublisher {

    @Inject
    CreateProductHandler createProductHandler = new CreateProductHandler();
    @Inject
    UpdateProductHandler updateProductHandler = new UpdateProductHandler();
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public Product publishAndWait(Command command) {
        if (command
                instanceof CreateProduct)
            return createProduct(command);
        else
            return updateProduct(command);
    }

    private Product createProduct(Command command) {
        var subs = new CreateProductCommandSubscribable(createProductHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof CreateProductEventSubscribable)
                .map(sub -> (CreateProductEventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return CreateProductCategoryHandler.unitOfWork.productRepository()
                .getOneById(((CreateProductResponse) event.getResponse()).getProductId());
    }

    private Product updateProduct(Command command) {
        var subs = new UpdateProductCommandSubscribable(updateProductHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof UpdateProductEventSubscribable)
                .map(sub -> (UpdateProductEventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return CreateProductCategoryHandler.unitOfWork.productRepository()
                .getOneById(((UpdateProductResponse) event.getResponse()).getProductId());
    }

}
