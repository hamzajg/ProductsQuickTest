package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest

public class CreateProductEndpointTest {

    @Test
    public void testCreateProductEndpoint() {
        var command = new CreateProduct();
        command.name = "Test product";
        command.categoryName = "Test Category product";
        command.unitPrice = 555;
        command.discount = 0;
        command.availableQty = 1000;
        given()
                .body(JsonbBuilder.create().toJson(command))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/products/create")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
