package pl.ksurowka.voucherstore.productcatalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JDBCPlaygroundTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        jdbcTemplate.execute("DROP TABLE products_catalog__products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `products_catalog__products` (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(150)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY (id)" +
                ");");
    }

    @Test
    public void countsProducts() {
        int result = jdbcTemplate.queryForObject("Select count(*) from `products_catalog__products`", Integer.class);

        assertThat(result).isEqualTo(0);
    }
}
