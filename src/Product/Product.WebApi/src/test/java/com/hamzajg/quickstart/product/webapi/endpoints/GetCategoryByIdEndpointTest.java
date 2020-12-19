package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest

public class GetCategoryByIdEndpointTest {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @Test
    public void testGetCategoryByIdEndpoint() {
        var command = new CreateProductCategory();
        command.name = "Test product";
        var category = productCategoryServicesFacade.createProductCategory(command);
        given()
                .when().get("/api/v1/product-categories/" + category.id)
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
