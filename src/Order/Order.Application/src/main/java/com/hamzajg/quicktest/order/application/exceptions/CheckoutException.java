package com.hamzajg.quicktest.order.application.exceptions;

/**
 * A checkout that cannot complete. The {@link #code()} is the machine-readable
 * reason the web layer turns into a status code — the same vocabulary the
 * specification's examples assert on, e.g. {@code PRODUCT_OUT_OF_STOCK}.
 */
public class CheckoutException extends RuntimeException {
    private final String code;

    public CheckoutException(String code) {
        super(code);
        this.code = code;
    }

    public String code() {
        return code;
    }
}
