package com.hamzajg.quicktest.order.infrastructure.gateways;

import com.hamzajg.quicktest.order.application.ports.CustomerGateway;
import com.hamzajg.quicktest.order.infrastructure.clients.CustomerClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;

@ApplicationScoped
public class RestCustomerGateway implements CustomerGateway {

    private final CustomerClient customerClient;

    @Inject
    public RestCustomerGateway(@RestClient CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public boolean exists(UUID customerId) {
        try {
            customerClient.getById(customerId.toString());
            return true;
        } catch (WebApplicationException e) {
            if (e.getResponse() != null && e.getResponse().getStatus() == 404)
                return false;
            throw e;
        }
    }
}
