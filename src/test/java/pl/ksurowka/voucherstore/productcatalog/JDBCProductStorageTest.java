package pl.ksurowka.voucherstore.productcatalog;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class JDBCProductStorageTest {
    @Test
    public void AllowsStoringProduct() {
        Product p1 = thereIsProduct();
        ProductStorage storage = new JDBCProductStorage();

        storage.save(p1);

        assertThat(storage.allPublishedProducts())
                .hasSize(1)
                .extracting(Product::getId)
                .contains(p1.getId());
    }

    private Product thereIsProduct() {
        Product product = new Product(UUID.randomUUID());
        product.setPrice(BigDecimal.valueOf(10));
        return new Product(UUID.randomUUID());
    }
}
