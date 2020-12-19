package com.hamzajg.quicktest.customer.domain.entities;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerLastNameChanged implements Event {
    public CustomerLastNameChanged(UUID id, String newLastName) {

    }
}
