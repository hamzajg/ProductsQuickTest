package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class UpdateCustomerEndpointTest {
    @Inject
    CustomerServicesFacade customerServicesFacade;

    @Test
    public void testUpdateCustomerEndpoint() {
        var command = new CreateCustomer();
        command.firstName = "Test";
        command.lastName = "customer";
        command.address = "test address";
        command.email = "test@tests.com";
        command.mobile = "1000";
        var customer = customerServicesFacade.createCustomer(command);
        var req = new UpdateCustomerRequest();
        req.firstName = "New Test Product";
        req.lastName = "customer";
        req.address = "test address";
        req.email = "test@tests.com";
        req.mobile = "99332244";
        given()
                .body(JsonbBuilder.create().toJson(command))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().patch("/api/v1/customers/" + customer.id + "/update")
                .then()
                .statusCode(200)
                .body(not(""));
    }

}