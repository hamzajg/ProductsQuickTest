package com.hamzajg.quicktest.product.application.messaging;

import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;

public interface ProductCommandReqResPublisher {
    Product publishAndWait(Command command);
}
