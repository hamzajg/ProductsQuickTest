package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public class DeleteProduct implements Command {
    public String productId;

    public DeleteProduct(String productId) {
        this.productId = productId;
    }
}
