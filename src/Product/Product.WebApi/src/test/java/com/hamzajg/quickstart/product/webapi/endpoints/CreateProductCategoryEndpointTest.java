package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest

public class CreateProductCategoryEndpointTest {

    @Test
    public void testCreateProductCategoryEndpoint() {
        var command = new CreateProductCategory();
        command.name = "Test product";
        given()
                .body(JsonbBuilder.create().toJson(command))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/product-categories/create")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
