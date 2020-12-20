package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class DeleteProductCategoryEndpointTest {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @Test
    public void testDeleteProductCategoryEndpoint() {
        var command = new CreateProductCategory();
        command.name = "Test product";
        var category = productCategoryServicesFacade.createProductCategory(command);
        given()
                .when().delete("/api/v1/product-categories/" + category.id + "/delete")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
