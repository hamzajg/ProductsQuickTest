import com.hamzajg.quicktest.product.domain.entities.ProductCategory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ProductCategoryTest {

    @Test
    public void CanCreateProductCategory() {
        var productCategory = new ProductCategory("Test");
        assertNotNull(productCategory.events);
    }
}
