package pl.ksurowka.voucherstore.productcatalog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ksurowka.voucherstore.productcatalog.JDBCProductStorage;
import pl.ksurowka.voucherstore.productcatalog.Product;
import pl.ksurowka.voucherstore.productcatalog.ProductStorage;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void AllowsStoringProduct() {
        Product p1 = thereIsProduct();
        ProductStorage storage = new JDBCProductStorage();

        jdbcStorage.save(p1);

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

    private ProductStorage thereIsJDBCProductStorage() {
        return new
    }
}
