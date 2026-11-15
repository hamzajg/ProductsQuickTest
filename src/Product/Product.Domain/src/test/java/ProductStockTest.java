import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import com.hamzajg.quicktest.product.domain.exceptions.InsufficientStockException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProductStockTest {

    private Product productWithQty(int availableQty) {
        return new Product("Test", new ProductCategory("Test"), 10.0f, 0.0f, availableQty);
    }

    @Test
    // covers scenario: Reserve stock within availability
    public void reservingStockWithinAvailabilityDecrementsQuantity() {
        var product = productWithQty(10);
        product.reserve(4);
        assertEquals(6, product.availableQty());
    }

    @Test
    // covers scenario: Reserving beyond availability is rejected
    public void reservingBeyondAvailabilityIsRejected() {
        var product = productWithQty(3);
        try {
            product.reserve(5);
            fail("reserving more than available should be rejected");
        } catch (InsufficientStockException expected) {
            assertEquals(3, product.availableQty());
        }
    }

    @Test
    // covers scenario: Releasing reserved stock restores availability
    public void releasingReservedStockRestoresAvailability() {
        var product = productWithQty(10);
        product.reserve(4);
        product.release(4);
        assertEquals(10, product.availableQty());
    }

    @Test
    public void reservingANonPositiveQuantityIsRejected() {
        var product = productWithQty(10);
        try {
            product.reserve(0);
            fail("reserving zero should be rejected");
        } catch (IllegalArgumentException expected) {
            assertEquals(10, product.availableQty());
        }
    }
}
