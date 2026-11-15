package com.hamzajg.quicktest.order.infrastructure.clients;

/**
 * The body the products service's {@code reserve}/{@code release} endpoints
 * expect.
 */
public class StockChangeBody {
    public int qty;

    public StockChangeBody() {
    }

    public StockChangeBody(int qty) {
        this.qty = qty;
    }
}
