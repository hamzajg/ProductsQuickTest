package com.hamzajg.quicktest.product.domain.exceptions;

import java.util.UUID;

/**
 * Raised when a reservation asks for more stock than a product has available.
 * The caller maps it to HTTP 409 — the aggregate never lets availability go
 * negative.
 */
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(UUID productId, int requested, int available) {
        super("Cannot reserve " + requested + " of product " + productId
                + " — only " + available + " available");
    }
}
