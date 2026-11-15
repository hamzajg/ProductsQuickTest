package com.hamzajg.quickstart.order.webapi.endpoints;

import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;
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
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The orders service is the first caller of the other two services, so these
 * tests replace the two gateways: what is under test is the HTTP contract of
 * {@code POST /api/v1/orders/create} and the compensation it triggers, not the
 * REST clients (proven separately in the infrastructure module).
 */
@QuarkusTest
public class CreateOrderEndpointTest {

    @InjectMock
    CustomerGateway customerGateway;
    @InjectMock
    ProductGateway productGateway;

    private String orderBody(String customerId, Object... productIdAndQty) {
        var request = new CreateOrderRequest();
        request.customerId = customerId;
        request.items = new ArrayList<>();
        for (int i = 0; i < productIdAndQty.length; i += 2) {
            var item = new CreateOrderItemRequest();
            item.productId = (String) productIdAndQty[i];
            item.qty = (Integer) productIdAndQty[i + 1];
            request.items.add(item);
        }
        return JsonbBuilder.create().toJson(request);
    }

    private io.restassured.response.Response post(String body) {
        return given()
                .body(body)
                .header("content-type", MediaType.APPLICATION_JSON)
                .when().post("/api/v1/orders/create");
    }

    @Test
    public void placingAnInStockOrderReturnsThePlacedOrder() {
        var customerId = UUID.randomUUID().toString();
        var productId = UUID.randomUUID().toString();
        when(customerGateway.exists(any())).thenReturn(true);
        when(productGateway.reserve(any(), anyInt()))
                .thenReturn(new ProductSnapshot(productId, 5.0f, 8));

        post(orderBody(customerId, productId, 2))
                .then()
                .statusCode(200)
                .body("status", equalTo("PLACED"))
                .body("customerId", equalTo(customerId))
                .body("total", equalTo(10.0f))
                .body("items[0].productId", equalTo(productId))
                .body("items[0].qty", equalTo(2))
                .body("items[0].unitPrice", equalTo(5.0f));
    }

    @Test
    public void anUnknownCustomerIsReportedAsNotFound() {
        when(customerGateway.exists(any())).thenReturn(false);

        post(orderBody(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1))
                .then()
                .statusCode(404)
                .body("error", equalTo("CUSTOMER_NOT_FOUND"));

        verify(productGateway, never()).reserve(any(), anyInt());
    }

    @Test
    public void anUnknownProductIsReportedAsNotFound() {
        when(customerGateway.exists(any())).thenReturn(true);
        when(productGateway.reserve(any(), anyInt()))
                .thenThrow(new CheckoutException("PRODUCT_NOT_FOUND"));

        post(orderBody(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1))
                .then()
                .statusCode(404)
                .body("error", equalTo("PRODUCT_NOT_FOUND"));
    }

    @Test
    public void anOutOfStockLineIsReportedAsConflictAndReleasesTheHeldLines() {
        String first = UUID.randomUUID().toString();
        String second = UUID.randomUUID().toString();
        when(customerGateway.exists(any())).thenReturn(true);
        when(productGateway.reserve(eq(UUID.fromString(first)), anyInt()))
                .thenReturn(new ProductSnapshot(first, 5.0f, 9));
        when(productGateway.reserve(eq(UUID.fromString(second)), anyInt()))
                .thenThrow(new CheckoutException("PRODUCT_OUT_OF_STOCK"));

        post(orderBody(UUID.randomUUID().toString(), first, 1, second, 1))
                .then()
                .statusCode(409)
                .body("error", equalTo("PRODUCT_OUT_OF_STOCK"));

        // The line that was already held must be handed back.
        verify(productGateway).release(eq(UUID.fromString(first)), eq(1));
    }

    @Test
    public void anOrderWithoutItemsIsRejected() {
        post(orderBody(UUID.randomUUID().toString()))
                .then()
                .statusCode(400)
                .body("error", equalTo("INVALID_ORDER"));
    }
}
