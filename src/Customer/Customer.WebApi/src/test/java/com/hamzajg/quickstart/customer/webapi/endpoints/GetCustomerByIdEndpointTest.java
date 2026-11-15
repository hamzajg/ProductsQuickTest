package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class GetCustomerByIdEndpointTest {
    @Inject
    CustomerServicesFacade customerServicesFacade;

    @Test
    public void testGetCustomerByIdEndpoint() {
        var command = new CreateCustomer();
        command.firstName = "Test";
        command.lastName = "customer";
        command.address = "test address";
        command.email = "test@tests.com";
        command.mobile = "1000";
        var customer = customerServicesFacade.createCustomer(command);
        given()
                .when().get("/api/v1/customers/" + customer.id)
                .then()
                .statusCode(200)
                .body(not(""));
    }

    @Test
    public void anUnknownCustomerIsNotFound() {
        // The orders service tells "no such customer" from "customer" by this
        // status; without it the checkout cannot reject an unknown customer.
        given()
                .when().get("/api/v1/customers/" + java.util.UUID.randomUUID())
                .then()
                .statusCode(404);
    }

}