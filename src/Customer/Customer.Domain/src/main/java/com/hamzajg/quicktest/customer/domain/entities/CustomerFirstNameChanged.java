package com.hamzajg.quicktest.customer.domain.entities;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerFirstNameChanged implements Event {
    public CustomerFirstNameChanged(UUID id, String newFirstName) {
    }
}
