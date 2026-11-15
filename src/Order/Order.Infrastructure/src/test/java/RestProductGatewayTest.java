import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;
import com.hamzajg.quicktest.order.infrastructure.clients.ProductClient;
import com.hamzajg.quicktest.order.infrastructure.clients.ProductView;
import com.hamzajg.quicktest.order.infrastructure.clients.StockChangeBody;
import com.hamzajg.quicktest.order.infrastructure.clients.StockView;
import com.hamzajg.quicktest.order.infrastructure.gateways.RestProductGateway;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The products service's HTTP answers mean different things to a checkout:
 * 404 is "no such product", 409 is "not enough of it". These pin that mapping,
 * which the compose smoke test only covers end to end.
 */
class RestProductGatewayTest {

    private final StubProductClient client = new StubProductClient();
    private final RestProductGateway gateway = new RestProductGateway(client);

    @Test
    void aMissingProductIsReportedAsProductNotFound() {
        client.reserveFailure = status(404);

        var failure = assertThrows(CheckoutException.class,
                () -> gateway.reserve(UUID.randomUUID(), 1));

        assertEquals("PRODUCT_NOT_FOUND", failure.code());
    }

    @Test
    void aShortageIsReportedAsProductOutOfStock() {
        client.reserveFailure = status(409);

        var failure = assertThrows(CheckoutException.class,
                () -> gateway.reserve(UUID.randomUUID(), 5));

        assertEquals("PRODUCT_OUT_OF_STOCK", failure.code());
    }

    @Test
    void aHeldLineIsPricedFromTheProduct() {
        var productId = UUID.randomUUID();
        client.product = productView(productId.toString(), 5.0f, 8);

        var snapshot = gateway.reserve(productId, 2);

        assertEquals(productId.toString(), snapshot.productId());
        assertEquals(5.0f, snapshot.unitPrice(), 0.0001f);
        assertEquals(8, snapshot.availableQty());
    }

    @Test
    void aFailedReleaseDoesNotReplaceTheCheckoutFailure() {
        client.releaseFailure = status(500);

        gateway.release(UUID.randomUUID(), 2);

        assertTrue(true, "a best-effort release must not throw");
    }

    private static WebApplicationException status(int status) {
        return new WebApplicationException(Response.status(status).build());
    }

    private static ProductView productView(String id, float unitPrice, int availableQty) {
        var view = new ProductView();
        view.id = id;
        view.unitPrice = unitPrice;
        view.availableQty = availableQty;
        return view;
    }

    private static class StubProductClient implements ProductClient {
        WebApplicationException reserveFailure;
        WebApplicationException releaseFailure;
        ProductView product;

        @Override
        public StockView reserve(String productId, StockChangeBody body) {
            if (reserveFailure != null)
                throw reserveFailure;
            return new StockView(productId, 0);
        }

        @Override
        public StockView release(String productId, StockChangeBody body) {
            if (releaseFailure != null)
                throw releaseFailure;
            return new StockView(productId, 0);
        }

        @Override
        public ProductView getById(String productId) {
            return product;
        }
    }
}
