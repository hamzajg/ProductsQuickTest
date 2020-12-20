package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.product.application.usecases.DeleteProductCategoryUseCase;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.DeleteProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.DeleteProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class DeleteProductCategoryHandler implements CommandHandler {

    @Inject
    DeleteProductCategoryUseCase deleteProductCategoryUseCase = new DeleteProductCategoryUseCase(CreateProductCategoryHandler.unitOfWork);
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = deleteProductCategoryUseCase.execute(UUID.fromString(((DeleteProductCategory) command).productCategoryId));

        var subs = new UpdateProductCategoryEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new DeleteProductCategoryResponse(result));
    }
}
