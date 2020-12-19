package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class UpdateProductEndpointTest {
    @Inject
    ProductServicesFacade productServicesFacade;
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    @Test
    public void testUpdateProductEndpoint() {
        var command = new CreateProductCategory();
        command.name = "Test product";
        var category = productCategoryServicesFacade.createProductCategory(command);
        var commandProduct = new CreateProduct();
        commandProduct.name = "Test product";
        commandProduct.categoryId = category.id;
        commandProduct.unitPrice = 555;
        commandProduct.discount = 0;
        commandProduct.availableQty = 1000;
        var product = productServicesFacade.createProduct(commandProduct);
        var req = new UpdateProductRequest();
        req.name = "New Test Product";
        req.categoryId = category.id;
        req.unitPrice = 555;
        req.discount = 0;
        req.availableQty = 1000;
        given()
                .body(JsonbBuilder.create().toJson(req))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().patch("/api/v1/products/" + product.id + "/update")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}
