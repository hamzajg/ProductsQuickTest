package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public class DeleteProductCategory implements Command {
    public String productCategoryId;
    public DeleteProductCategory(String productCategoryId) {

        this.productCategoryId = productCategoryId;
    }
}
