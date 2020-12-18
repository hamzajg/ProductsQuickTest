package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public class CreateProduct implements Command {
    public String name;
    public String categoryName;
    public float unitPrice;
    public float discount;
    public Integer availableQty;
}
