package com.hamzajg.quicktest.product.infrastructure.messaging.product.delete;

import com.hamzajg.quicktest.product.application.usecases.DeleteProductUseCase;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.category.create.CreateProductCategoryHandler;
import com.hamzajg.quicktest.product.infrastructure.messaging.product.category.update.UpdateProductCategoryEventSubscribable;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.DeleteProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.DeleteProductResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class DeleteProductHandler implements CommandHandler {

    @Inject
    DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(CreateProductCategoryHandler.unitOfWork);
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = deleteProductUseCase.execute(UUID.fromString(((DeleteProduct) command).productId));

        var subs = new DeleteProductEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new DeleteProductResponse(result));
    }
}
