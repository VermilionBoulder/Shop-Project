package pl.ksurowka.voucherstore.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Configuration
public class ProductCatalogConfiguration {

    public ProductCatalogFacade productCatalogFacade() {
        return new ProductCatalogFacade();
    }

    @Bean
    public ProductCatalogFacade fixturesAwareProductCatalogFacade() {

        ProductCatalogFacade productCatalogFacade = new ProductCatalogFacade();

        String p1 = productCatalogFacade.createProduct();
        productCatalogFacade.applyPrice(p1, BigDecimal.valueOf(10.15));
        productCatalogFacade.updateProductDetails(p1, "My fancy product 1", "http://sample.jpeg");

        String p2 = productCatalogFacade.createProduct();
        productCatalogFacade.applyPrice(p2, BigDecimal.valueOf(20.30));
        productCatalogFacade.updateProductDetails(p2, "My fancy product 2", "http://sample.jpeg");

        String p3 = productCatalogFacade.createProduct();
        productCatalogFacade.applyPrice(p3, BigDecimal.valueOf(30.45));
        productCatalogFacade.updateProductDetails(p3, "My fancy product 3", "http://sample.jpeg");

        return productCatalogFacade;
    }
}
