package pl.ksurowka.voucherstore.sales;

import org.junit.Before;
import org.junit.Test;
import pl.ksurowka.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.ksurowka.voucherstore.productcatalog.ProductCatalogFacade;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesTest {

    ProductCatalogFacade productCatalog;
    private InMemoryBasketStorage inMemoryBasketStorage;
    private CurrentCustomerContext currentCustomerContext;
    private Inventory inventory;
    String customerId;

    @Before
    public void setup() {
        productCatalog = new ProductCatalogConfiguration().productCatalogFacade();
        inMemoryBasketStorage = new InMemoryBasketStorage();
        inventory = productId -> true;
        currentCustomerContext = () -> customerId;
    }

    @Test
    public void itAllowsAddingProductToBasket() {
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductAvailable();
        var productId2 = thereIsProductAvailable();
        var customerId = thereIsCustomer();

        salesFacade.addProduct(productId1);
        salesFacade.addProduct(productId2);

        thereIsXProductCountInCustomerBasket(2, customerId);
    }

    @Test
    public void itAllowsAddingProductToBasketByMultipleUsers() {
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductAvailable();
        var productId2 = thereIsProductAvailable();

        var customerId1 = thereIsCustomer();
        salesFacade.addProduct(productId1);
        salesFacade.addProduct(productId2);

        var customerId2 = thereIsCustomer();
        salesFacade.addProduct(productId1);

        thereIsXProductCountInCustomerBasket(2, customerId1);
        thereIsXProductCountInCustomerBasket(1, customerId2);
    }

    private void thereIsXProductCountInCustomerBasket(int expectedProductsCount, String customerId) {
        Basket basket = inMemoryBasketStorage.loadForCustomer(customerId)
                .orElse(Basket.empty());

        assertThat(basket.getProductsCount()).isEqualTo(expectedProductsCount);
    }

    private String thereIsCustomer() {
        customerId = UUID.randomUUID().toString();
        return new String(customerId);
    }

    private String thereIsProductAvailable() {
        var id = productCatalog.createProduct();
        productCatalog.applyPrice(id, BigDecimal.valueOf(10));
        productCatalog.updateProductDetails(id, "desc", "http://image");
        return id;
    }


    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(
                productCatalog,
                inMemoryBasketStorage,
                currentCustomerContext,
                inventory
        );
    }
}
