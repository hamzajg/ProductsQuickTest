package com.hamzajg.quicktest.customer.domain.entities;

import com.hamzajg.quicktest.customer.domain.events.Event;

import java.util.UUID;

public class CustomerAddressChanged implements Event {
    public CustomerAddressChanged(UUID id, String newAddress) {

    }
}
