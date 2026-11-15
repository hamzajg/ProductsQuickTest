package com.hamzajg.quicktest.order.application.ports;

import java.util.UUID;

/**
 * Stock access to the products service. {@link #reserve} is the hold a checkout
 * takes on a line; {@link #release} gives it back when a later line fails, so
 * the orchestrator can compensate instead of abandoning held stock.
 *
 * <p>Implementations translate the products service's answers into
 * {@code CheckoutException}s: a missing product and a shortage are different
 * outcomes the caller reacts to differently.
 */
public interface ProductGateway {
    ProductSnapshot reserve(UUID productId, int qty);

    void release(UUID productId, int qty);
}
