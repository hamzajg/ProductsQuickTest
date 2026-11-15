package com.hamzajg.quicktest.order.application.ports;

import java.util.UUID;

/**
 * Read access to the customers service. The orders service only needs to know
 * whether the customer placing the order actually exists.
 */
public interface CustomerGateway {
    boolean exists(UUID customerId);
}
