package com.hamzajg.quickstart.customer.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateCustomer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class CreateCustomerEndpointTest {

    @Test
    public void testCreateCustomerEndpoint() {
        var command = new CreateCustomer();
        command.firstName = "Test";
        command.lastName = "customer";
        command.address = "test address";
        command.email = "test@tests.com";
        command.mobile = "1000";
        given()
                .body(JsonbBuilder.create().toJson(command))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/customers/create")
                .then()
                .statusCode(200)
                .body(not(""));
    }

}