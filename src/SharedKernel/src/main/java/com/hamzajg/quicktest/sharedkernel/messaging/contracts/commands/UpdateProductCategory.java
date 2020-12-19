package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public class UpdateProductCategory implements Command {
    public String name;
    public String productCategoryId;

    public UpdateProductCategory(String productCategoryId, String name) {
        this.productCategoryId = productCategoryId;
        this.name = name;
    }
}
