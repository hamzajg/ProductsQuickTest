package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest

public class GetAllProductCategoriesEndpointTest {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @Test
    public void testGetAllProductCategoriesEndpoint() {
        var command = new CreateProductCategory();
        command.name = "Test product";
        productCategoryServicesFacade.createProductCategory(command);
        given()
                .when().get("/api/v1/product-categories/all")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
