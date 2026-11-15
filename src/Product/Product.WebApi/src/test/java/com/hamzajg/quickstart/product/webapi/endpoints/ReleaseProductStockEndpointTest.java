package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quickstart.product.webapi.endpoints.product.ProductServicesFacade;
import com.hamzajg.quickstart.product.webapi.endpoints.product.category.ProductCategoryServicesFacade;
import com.hamzajg.quickstart.product.webapi.endpoints.product.stock.StockChangeRequest;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProduct;
import com.hamzajg.quicktest.sharedkernel.messaging.contracts.commands.CreateProductCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class ReleaseProductStockEndpointTest {
    @Inject
    ProductServicesFacade productServicesFacade;
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    private String aProductWithAvailableQty(int availableQty) {
        var categoryCommand = new CreateProductCategory();
        categoryCommand.name = "Release category";
        var category = productCategoryServicesFacade.createProductCategory(categoryCommand);

        var productCommand = new CreateProduct();
        productCommand.name = "Released product";
        productCommand.categoryId = category.id;
        productCommand.unitPrice = 5.0f;
        productCommand.discount = 0f;
        productCommand.availableQty = availableQty;
        return productServicesFacade.createProduct(productCommand).id;
    }

    private String aStockChangeFor(int qty) {
        var req = new StockChangeRequest();
        req.qty = qty;
        return JsonbBuilder.create().toJson(req);
    }

    @Test
    // covers scenario: Releasing reserved stock restores availability
    public void releasingReservedStockRestoresAvailability() {
        var productId = aProductWithAvailableQty(10);

        given()
                .body(aStockChangeFor(4))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/products/" + productId + "/reserve")
                .then()
                .statusCode(200)
                .body("availableQty", equalTo(6));

        given()
                .body(aStockChangeFor(4))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/products/" + productId + "/release")
                .then()
                .statusCode(200)
                .body("availableQty", equalTo(10));
    }
}
