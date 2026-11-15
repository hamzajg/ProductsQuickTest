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
public class ReserveProductStockEndpointTest {
    @Inject
    ProductServicesFacade productServicesFacade;
    @Inject
    ProductCategoryServicesFacade productCategoryServicesFacade;

    private String aProductWithAvailableQty(int availableQty) {
        var categoryCommand = new CreateProductCategory();
        categoryCommand.name = "Reserve category";
        var category = productCategoryServicesFacade.createProductCategory(categoryCommand);

        var productCommand = new CreateProduct();
        productCommand.name = "Reserved product";
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
    // covers scenario: Reserve stock within availability
    public void reservingStockWithinAvailabilityDecrementsQuantity() {
        var productId = aProductWithAvailableQty(10);

        given()
                .body(aStockChangeFor(4))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/products/" + productId + "/reserve")
                .then()
                .statusCode(200)
                .body("availableQty", equalTo(6));
    }

    @Test
    // covers scenario: Reserving beyond availability is rejected
    public void reservingBeyondAvailabilityIsRejected() {
        var productId = aProductWithAvailableQty(3);

        given()
                .body(aStockChangeFor(5))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/products/" + productId + "/reserve")
                .then()
                .statusCode(409)
                .body("error", equalTo("PRODUCT_OUT_OF_STOCK"));

        given()
                .body(aStockChangeFor(3))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/products/" + productId + "/reserve")
                .then()
                .statusCode(200)
                .body("availableQty", equalTo(0));
    }

    @Test
    public void reservingAnUnknownProductIsRejected() {
        given()
                .body(aStockChangeFor(1))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/products/" + java.util.UUID.randomUUID() + "/reserve")
                .then()
                .statusCode(404)
                .body("error", equalTo("PRODUCT_NOT_FOUND"));
    }
}
