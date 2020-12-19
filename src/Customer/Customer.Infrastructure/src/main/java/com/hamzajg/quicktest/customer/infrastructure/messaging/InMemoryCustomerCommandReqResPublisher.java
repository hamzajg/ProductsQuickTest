package com.hamzajg.quicktest.customer.infrastructure.messaging;

import com.hamzajg.quicktest.customer.application.messaging.CustomerCommandReqResPublisher;
import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.CreateCustomerResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.responses.UpdateCustomerResponse;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Bus;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.BusFactory;
import com.hamzajg.quicktest.sharedkernel.messaging.inmemory.Exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InMemoryCustomerCommandReqResPublisher implements CustomerCommandReqResPublisher {

    @Inject
    CreateCustomerHandler createCustomerHandler = new CreateCustomerHandler();
    @Inject
    UpdateCustomerHandler updateCustomerHandler = new UpdateCustomerHandler();
    private final Bus bus = BusFactory.createSingletonSyncBus();

    @Override
    public Customer publishAndWait(Command command) {
        if (command instanceof CreateCustomer)
            return createCustomer(command);
        else
            return updateCustomer(command);
    }

    private Customer createCustomer(Command command) {
        var subs = new CreateCustomerCommandSubscribable(createCustomerHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof CreateCustomerEventSubscribable)
                .map(sub -> (CreateCustomerEventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return CreateCustomerHandler.unitOfWork.customerRepository().getOneById(((CreateCustomerResponse) event.getResponse()).getCustomerId());
    }

    private Customer updateCustomer(Command command) {
        var subs = new UpdateCustomerCommandSubscribable(updateCustomerHandler);
        bus.register(subs);

        bus.dispatch((Exchange<Command>) () -> command);
        var event = bus.getSubscribers()
                .stream()
                .filter(sub -> sub instanceof UpdateCustomerEventSubscribable)
                .map(sub -> (UpdateCustomerEventSubscribable) sub)
                .findAny().orElse(null);
        if (event == null)
            return null;
        return CreateCustomerHandler.unitOfWork.customerRepository()
                .getOneById(((UpdateCustomerResponse) event.getResponse())
                        .getCustomerId());
    }
}
