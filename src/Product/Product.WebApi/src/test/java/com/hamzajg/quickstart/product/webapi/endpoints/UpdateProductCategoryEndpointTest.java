package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;
import com.hamzajg.quickstart.product.webapi.endpoints.product.category.update.UpdateProductCategoryRequest;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class UpdateProductCategoryEndpointTest {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @Test
    public void testUpdateProductCategoryEndpoint() {
        var command = new CreateProductCategory();
        command.name = "Test product";
        var category = productCategoryServicesFacade.createProductCategory(command);
        var req = new UpdateProductCategoryRequest();
        req.name = "New Test Product";
        given()
                .body(JsonbBuilder.create().toJson(req))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().patch("/api/v1/product-categories/" + category.id + "/update")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
