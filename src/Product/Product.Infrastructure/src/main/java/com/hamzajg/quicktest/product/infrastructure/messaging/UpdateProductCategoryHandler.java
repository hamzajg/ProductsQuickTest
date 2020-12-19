package com.hamzajg.quicktest.product.infrastructure.messaging;

import com.hamzajg.quicktest.product.application.usecases.UpdateProductCategoryUseCase;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.UpdateProductCategoryResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class UpdateProductCategoryHandler implements CommandHandler {

    @Inject
    UpdateProductCategoryUseCase updateProductCategoryUseCase = new UpdateProductCategoryUseCase(CreateProductCategoryHandler.unitOfWork);
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = updateProductCategoryUseCase.execute(UUID.fromString(((UpdateProductCategory) command).productCategoryId), ((UpdateProductCategory) command).name);

        var subs = new UpdateProductCategoryEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new UpdateProductCategoryResponse(result.id()));
    }
}
