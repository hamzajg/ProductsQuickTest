package com.hamzajg.quicktest.customer.domain.events;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerLastNameChanged implements Event {
    public CustomerLastNameChanged(UUID id, String newLastName) {

    }
}
