import com.hamzajg.quicktest.order.application.UnitOfWork;
import com.hamzajg.quicktest.order.application.exceptions.CheckoutException;
import com.hamzajg.quicktest.order.application.ports.CustomerGateway;
import com.hamzajg.quicktest.order.application.ports.ProductGateway;
import com.hamzajg.quicktest.order.application.ports.ProductSnapshot;
import com.hamzajg.quicktest.order.application.usecases.PlaceOrderItem;
import com.hamzajg.quicktest.order.application.usecases.PlaceOrderUseCase;
import com.hamzajg.quicktest.order.domain.entities.Order;
import com.hamzajg.quicktest.order.domain.entities.OrderRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * The wave-2 examples, driven through the use case with the two remote services
 * replaced by fakes. A pass means the orchestration — validation, holds, and
 * the compensating release — behaves the same wherever the gateways point.
 */
public class PlaceOrderUseCaseTest {

    private final InMemoryOrderRepository orders = new InMemoryOrderRepository();
    private final FakeCustomers customers = new FakeCustomers();
    private final FakeProducts products = new FakeProducts();
    private final PlaceOrderUseCase useCase =
            new PlaceOrderUseCase(new UnitOfWork(orders), customers, products);

    @Test
    public void aCustomerPlacesAnInStockOrder() {
        var customerId = customers.aCustomer();
        var productId = products.aProduct(10, 5.0f);

        var order = useCase.execute(customerId, List.of(new PlaceOrderItem(productId, 2)));

        assertEquals("PLACED", order.status());
        assertEquals(customerId, order.customerId());
        assertEquals(10.0f, order.total(), 0.0001f);
        assertEquals(1, order.items().size());
        assertEquals(1, orders.getAll().size());
    }

    @Test
    public void aPlacedOrderDecrementsStock() {
        var customerId = customers.aCustomer();
        var productId = products.aProduct(10, 5.0f);

        useCase.execute(customerId, List.of(new PlaceOrderItem(productId, 2)));

        assertEquals(8, products.availableQty(productId));
    }

    @Test
    public void anUnknownCustomerCannotOrder() {
        var productId = products.aProduct(10, 5.0f);

        try {
            useCase.execute(UUID.randomUUID(), List.of(new PlaceOrderItem(productId, 1)));
            fail("an unknown customer should not be able to order");
        } catch (CheckoutException expected) {
            assertEquals("CUSTOMER_NOT_FOUND", expected.code());
            assertTrue(orders.getAll().isEmpty());
            assertEquals(10, products.availableQty(productId));
        }
    }

    @Test
    public void anUnknownProductCannotBeOrdered() {
        var customerId = customers.aCustomer();

        try {
            useCase.execute(customerId, List.of(new PlaceOrderItem(UUID.randomUUID(), 1)));
            fail("an unknown product should not be orderable");
        } catch (CheckoutException expected) {
            assertEquals("PRODUCT_NOT_FOUND", expected.code());
            assertTrue(orders.getAll().isEmpty());
        }
    }

    @Test
    public void anOutOfStockLineRollsTheWholeCheckoutBack() {
        var customerId = customers.aCustomer();
        var first = products.aProduct(10, 5.0f);
        var second = products.aProduct(1, 2.0f);

        try {
            useCase.execute(customerId, List.of(
                    new PlaceOrderItem(first, 1),
                    new PlaceOrderItem(second, 5)));
            fail("a line that cannot be held should fail the whole checkout");
        } catch (CheckoutException expected) {
            assertEquals("PRODUCT_OUT_OF_STOCK", expected.code());
            assertEquals(10, products.availableQty(first));
            assertEquals(1, products.availableQty(second));
            assertTrue(orders.getAll().isEmpty());
        }
    }

    // ---- fakes ------------------------------------------------------------

    private static class InMemoryOrderRepository implements OrderRepository {
        private final List<Order> orders = new ArrayList<>();

        @Override
        public Collection<Order> getAll() {
            return orders;
        }

        @Override
        public Order getOneById(UUID id) {
            return orders.stream().filter(o -> o.id().equals(id)).findFirst().orElse(null);
        }

        @Override
        public Order save(Order order) {
            orders.add(order);
            return order;
        }
    }

    private static class FakeCustomers implements CustomerGateway {
        private final Set<UUID> known = new HashSet<>();

        UUID aCustomer() {
            var id = UUID.randomUUID();
            known.add(id);
            return id;
        }

        @Override
        public boolean exists(UUID customerId) {
            return known.contains(customerId);
        }
    }

    private static class FakeProducts implements ProductGateway {
        private final Map<UUID, Float> prices = new HashMap<>();
        private final Map<UUID, Integer> qty = new HashMap<>();

        UUID aProduct(int availableQty, float unitPrice) {
            var id = UUID.randomUUID();
            prices.put(id, unitPrice);
            qty.put(id, availableQty);
            return id;
        }

        int availableQty(UUID productId) {
            return qty.getOrDefault(productId, 0);
        }

        @Override
        public ProductSnapshot reserve(UUID productId, int requested) {
            if (!prices.containsKey(productId))
                throw new CheckoutException("PRODUCT_NOT_FOUND");
            if (requested > availableQty(productId))
                throw new CheckoutException("PRODUCT_OUT_OF_STOCK");
            qty.put(productId, availableQty(productId) - requested);
            return new ProductSnapshot(productId.toString(), prices.get(productId), availableQty(productId));
        }

        @Override
        public void release(UUID productId, int released) {
            qty.put(productId, availableQty(productId) + released);
        }
    }
}
