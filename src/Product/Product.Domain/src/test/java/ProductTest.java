import com.hamzajg.quicktest.product.domain.entities.Product;
import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ProductTest {

    @Test
    public void CanCreateProduct() {
        var product = new Product("Test", new ProductCategory("Test"), 55.6f, 15.5f, 200);
        assertNotNull(product.events);
    }
}
