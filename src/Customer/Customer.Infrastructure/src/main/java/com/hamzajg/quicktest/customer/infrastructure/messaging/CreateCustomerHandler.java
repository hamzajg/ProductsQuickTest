package com.hamzajg.quicktest.customer.infrastructure.messaging;

import com.hamzajg.quicktest.customer.application.services.WriteCustomerService;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CommandHandler;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.CreateCustomerResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.Response;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CreateCustomerHandler implements CommandHandler {
    private final WriteCustomerService writeCustomerService;

    @Inject
    public CreateCustomerHandler(WriteCustomerService writeCustomerService) {
        this.writeCustomerService = writeCustomerService;
    }

    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public void handle(Command command) {
        var result = writeCustomerService.createCustomer((CreateCustomer) command);

        var subs = new CreateCustomerEventSubscribable();
        bus.register(subs);

        bus.dispatch((Exchange<Response>) () -> new CreateCustomerResponse(result.id()));
    }
}
