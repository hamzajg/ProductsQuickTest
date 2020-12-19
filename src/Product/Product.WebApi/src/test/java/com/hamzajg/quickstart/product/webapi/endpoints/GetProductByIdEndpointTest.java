package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest

public class GetProductByIdEndpointTest {
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;
    @Inject
    ProductServicesFacade productServicesFacade;

    @Test
    public void testGetProductByIdEndpoint() {
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
        given()
                .when().get("/api/v1/products/" + product.id + "/all")
                .then()
                .statusCode(200)
                .body(not(""));
    }
}