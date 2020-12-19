package com.hamzajg.quicktest.customer.infrastructure.messaging;

import com.hamzajg.quicktest.customer.application.services.BaseCustomerService;
import com.hamzajg.quicktest.customer.application.services.WriteCustomerService;
import com.hamzajg.quicktest.customer.application.usecases.CreateCustomerUseCase;
import com.hamzajg.quicktest.customer.application.usecases.UpdateCustomerUseCase;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.UpdateCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.UpdateCustomerResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateCustomerHandler implements CommandHandler {
    @Inject
    WriteCustomerService writeCustomerService = new BaseCustomerService(new CreateCustomerUseCase(CreateCustomerHandler.unitOfWork), new UpdateCustomerUseCase(CreateCustomerHandler.unitOfWork));
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = writeCustomerService.updateCustomer((UpdateCustomer) command);

        var subs = new UpdateCustomerEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new UpdateCustomerResponse(result.id()));
    }
}
