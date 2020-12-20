package com.hamzajg.quicktest.product.infrastructure.messaging.product.update;

import com.hamzajg.quicktest.product.application.usecases.UpdateProductUseCase;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.category.create.CreateProductCategoryHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.UpdateProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class UpdateProductHandler implements CommandHandler {

    @Inject
    UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(CreateProductCategoryHandler.unitOfWork);
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = updateProductUseCase.execute(UUID.fromString(((UpdateProduct) command).productId),
                UUID.fromString(((UpdateProduct) command).productCategoryId),
                ((UpdateProduct) command).name,
                ((UpdateProduct) command).unitPrice,
                ((UpdateProduct) command).discount,
                ((UpdateProduct) command).availableQty);

        var subs = new UpdateProductEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new UpdateProductResponse(result.id()));
    }
}
