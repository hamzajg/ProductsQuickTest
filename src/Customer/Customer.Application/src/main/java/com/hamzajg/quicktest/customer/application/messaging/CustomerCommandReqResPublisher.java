package com.hamzajg.quicktest.customer.application.messaging;

import com.hamzajg.quicktest.customer.domain.entities.Customer;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;

public interface CustomerCommandReqResPublisher {
    Customer publishAndWait(Command command);

}
