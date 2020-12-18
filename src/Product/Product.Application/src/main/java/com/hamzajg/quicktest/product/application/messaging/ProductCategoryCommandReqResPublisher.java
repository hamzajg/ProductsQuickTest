package com.hamzajg.quicktest.product.application.messaging;

import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.Command;

public interface ProductCategoryCommandReqResPublisher {
    ProductCategory publishAndWait(Command command);
}
