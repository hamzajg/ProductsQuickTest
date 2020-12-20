package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class DeleteCustomerEndpointTest {
    @Inject
    CustomerServicesFacade customerServicesFacade;

    @Test
    public void testDeleteCustomerEndpoint() {
        var command = new CreateCustomer();
        command.firstName = "Test";
        command.lastName = "customer";
        command.address = "test address";
        command.email = "test@tests.com";
        command.mobile = "1000";
        var customer = customerServicesFacade.createCustomer(command);
        given()
                .when().delete("/api/v1/customers/" + customer.id + "/delete")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
