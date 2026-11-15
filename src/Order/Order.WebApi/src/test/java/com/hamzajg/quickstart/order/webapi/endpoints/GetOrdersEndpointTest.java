package com.hamzajg.quickstart.order.webapi.endpoints;

import com.hamzajg.quicktest.order.application.ports.CustomerGateway;
import com.hamzajg.quicktest.order.application.ports.ProductGateway;
import com.hamzajg.quicktest.order.application.ports.ProductSnapshot;
import com.hamzajg.quickstart.order.webapi.endpoints.orders.CreateOrderItemRequest;
import com.hamzajg.quickstart.order.webapi.endpoints.orders.CreateOrderRequest;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * covers scenario: Order history is readable through the gateway
 * covers scenario: An unknown order id is not found
 *
 * The read side of the orders service: a placed order must be listable and
 * fetchable by id through the same gateway route the checkout uses, and an id
 * that was never placed must answer 404 ORDER_NOT_FOUND.
 */
@QuarkusTest
public class GetOrdersEndpointTest {

    @InjectMock
    CustomerGateway customerGateway;
    @InjectMock
    ProductGateway productGateway;

    private String placeOrder() {
        var customerId = UUID.randomUUID().toString();
        var productId = UUID.randomUUID().toString();
        when(customerGateway.exists(any())).thenReturn(true);
        when(productGateway.reserve(any(), anyInt()))
                .thenReturn(new ProductSnapshot(productId, 5.0f, 8));

        var item = new CreateOrderItemRequest();
        item.productId = productId;
        item.qty = 1;
        var request = new CreateOrderRequest();
        request.customerId = customerId;
        request.items = new ArrayList<>();
        request.items.add(item);

        var response = given()
                .body(JsonbBuilder.create().toJson(request))
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/orders/create");
        response.then().statusCode(200);
        return response.jsonPath().getString("orderId");
    }

    @Test
    public void aPlacedOrderAppearsInTheListAndById() {
        var orderId = placeOrder();

        given().when().get("/api/v1/orders/all")
                .then()
                .statusCode(200)
                .body("items.orderId", hasItem(orderId));

        given().when().get("/api/v1/orders/" + orderId)
                .then()
                .statusCode(200)
                .body("orderId", equalTo(orderId))
                .body("status", equalTo("PLACED"));
    }

    @Test
    public void anUnknownOrderIdIsNotFound() {
        given().when().get("/api/v1/orders/" + UUID.randomUUID())
                .then()
                .statusCode(404)
                .body("error", equalTo("ORDER_NOT_FOUND"));
    }

    @Test
    public void theListDoesNotContainUnplacedOrders() {
        placeOrder();

        given().when().get("/api/v1/orders/all")
                .then()
                .statusCode(200)
                .body("items", hasSize(org.hamcrest.Matchers.greaterThanOrEqualTo(1)))
                .body("items.orderId", not(hasItem(UUID.randomUUID().toString())));
    }
}
