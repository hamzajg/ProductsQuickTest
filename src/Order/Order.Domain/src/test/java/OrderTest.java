import com.hamzajg.quicktest.order.domain.entities.Order;
import com.hamzajg.quicktest.order.domain.entities.OrderItem;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrderTest {

    @Test
    public void anOrderTotalsItsLines() {
        var order = new Order(UUID.randomUUID(), List.of(
                new OrderItem(UUID.randomUUID(), 2, 5.0f),
                new OrderItem(UUID.randomUUID(), 1, 2.5f)));

        assertEquals("PLACED", order.status());
        assertEquals(12.5f, order.total(), 0.0001f);
        assertEquals(2, order.items().size());
    }

    @Test
    public void anOrderWithNoItemsIsRejected() {
        try {
            new Order(UUID.randomUUID(), Collections.emptyList());
            fail("an order without items should be rejected");
        } catch (IllegalArgumentException expected) {
            // an order that bought nothing is not an order
        }
    }
}
