package pl.ksurowka.voucherstore.sales;

import org.junit.Test;

public class SalesTest {
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

    private void thereIsXProductCountInCustomerBasket(int i, String customerId) {

    }

    private String thereIsCustomer() {
        return null;
    }

    private String thereIsProductAvailable() {
        return null;
    }


    private SalesFacade thereIsSalesModule() {
        return null;
    }
}
