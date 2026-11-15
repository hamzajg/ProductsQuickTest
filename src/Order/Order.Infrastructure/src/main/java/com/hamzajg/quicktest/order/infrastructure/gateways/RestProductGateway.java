package com.hamzajg.quicktest.order.infrastructure.gateways;

import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;
import com.hamzajg.quicktest.order.application.ports.ProductGateway;
import com.hamzajg.quicktest.order.application.ports.ProductSnapshot;
import com.hamzajg.quicktest.order.infrastructure.clients.ProductClient;
import com.hamzajg.quicktest.order.infrastructure.clients.StockChangeBody;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;

@ApplicationScoped
public class RestProductGateway implements ProductGateway {

    private final ProductClient productClient;

    @Inject
    public RestProductGateway(@RestClient ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public ProductSnapshot reserve(UUID productId, int qty) {
        try {
            productClient.reserve(productId.toString(), new StockChangeBody(qty));
        } catch (WebApplicationException e) {
            throw asCheckoutFailure(e);
        }

        var product = productClient.getById(productId.toString());
        return new ProductSnapshot(product.id, product.unitPrice, product.availableQty);
    }

    @Override
    public void release(UUID productId, int qty) {
        try {
            productClient.release(productId.toString(), new StockChangeBody(qty));
        } catch (WebApplicationException e) {
            // Best effort: the checkout has already failed, and the failure the
            // caller must see is the one that caused the release, not this.
        }
    }

    private CheckoutException asCheckoutFailure(WebApplicationException e) {
        var status = e.getResponse() == null ? 0 : e.getResponse().getStatus();
        if (status == 404)
            return new CheckoutException("PRODUCT_NOT_FOUND");
        if (status == 409)
            return new CheckoutException("PRODUCT_OUT_OF_STOCK");
        throw e;
    }
}
