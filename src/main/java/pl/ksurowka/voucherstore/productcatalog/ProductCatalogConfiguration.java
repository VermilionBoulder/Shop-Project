package pl.ksurowka.voucherstore.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCatalogConfiguration {

    public ProductCatalogFacade productCatalogFacade() {
        return new ProductCatalogFacade();
    }

    @Bean
    public ProductCatalogFacade fixturesAwareProductCatalogFacade() {

        ProductCatalogFacade productCatalogFacade = new ProductCatalogFacade();


        return productCatalogFacade;
    }
}
