package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class GetAllCustomersEndpointTest {
    @Inject
    CustomerServicesFacade customerServicesFacade;

    @Test
    public void testCreateCustomerEndpoint() {
        var command = new CreateCustomer();
        command.firstName = "Test";
        command.lastName = "customer";
        command.address = "test address";
        command.email = "test@tests.com";
        command.mobile = "1000";
        customerServicesFacade.CreateCustomer(command);
        given()
                .when().get("/api/v1/customers/all")
                .then()
                .statusCode(200)
                .body(not(""));
    }

}