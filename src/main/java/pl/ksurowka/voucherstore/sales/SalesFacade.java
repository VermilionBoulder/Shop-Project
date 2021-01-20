package pl.ksurowka.voucherstore.sales;

import pl.ksurowka.voucherstore.productcatalog.Product;
import pl.ksurowka.voucherstore.productcatalog.ProductCatalogFacade;

public class SalesFacade {

    ProductCatalogFacade productCatalogFacade;
    InMemoryBasketStorage inMemoryBasketStorage;
    CurrentCustomerContext currentCustomerContext;
    Inventory inventory;

    public SalesFacade(ProductCatalogFacade productCatalogFacade, InMemoryBasketStorage inMemoryBasketStorage, CurrentCustomerContext currentCustomerContext, Inventory inventory) {
        this.productCatalogFacade = productCatalogFacade;
        this.inMemoryBasketStorage = inMemoryBasketStorage;
        this.currentCustomerContext = currentCustomerContext;
        this.inventory = inventory;
    }

    public void addProduct(String productId) {
        Product product = productCatalogFacade.getById(productId);
        Basket basket = inMemoryBasketStorage.loadForCustomer(getCurrentCustomerId())
                .orElse(Basket.empty());

        basket.add(product, inventory);

        inMemoryBasketStorage.addForCustomer(getCurrentCustomerId(), basket);
    }

    private String getCurrentCustomerId() {
        return currentCustomerContext.getCurrentCustomerId();
    }


}
