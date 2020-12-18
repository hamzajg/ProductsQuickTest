package com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands;

public interface CommandHandler {
    void handle(Command command);
}
