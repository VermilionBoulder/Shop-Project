package pl.ksurowka.voucherstore.sales;

import org.junit.Test;
import pl.ksurowka.voucherstore.productcatalog.Product;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class BasketTest {

    public static final String PRODUCT_1 = "lego-8273";
    public static final String PRODUCT_2 = "lego-8274";

    @Test
    public void newlyCreatedBasketIsEmpty() {
        //Arrange
        Basket basket = Basket.empty();
        //Act
        //Assert
        assertThat(basket.isEmpty())
                .isTrue();
    }

    @Test
    public void basketWithProductsIsNotEmpty() {
        //Arrange
        Basket basket = Basket.empty();
        Product product = thereIsProduct(PRODUCT_1);
        //Act
        basket.add(product, thereIsInventory());
        //Assert
        assertThat(basket.isEmpty())
                .isFalse();
    }

    @Test
    public void itShowsProductsCount() {
        //Arrange
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct(PRODUCT_1);
        Product product2 = thereIsProduct("lego-8274");
        //Act
        basket.add(product1, thereIsInventory());
        basket.add(product2, thereIsInventory());
        //Assert
        assertThat(basket.getProductsCount())
                .isEqualTo(2);
    }

    @Test
    public void itCountsDuplicateProductsOnlyOnce() {
        //Arrange
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct(PRODUCT_1);
        //Act
        basket.add(product1, thereIsInventory());
        basket.add(product1, thereIsInventory());
        //Assert
        assertThat(basket.getProductsCount())
                .isEqualTo(1);
        basketContainsProductWithQuantity(basket, product1, 2);
    }

    @Test
    public void itStoresQuantityForMultipleProducts() {
        //Arrange
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct(PRODUCT_1);
        Product product2 = thereIsProduct(PRODUCT_2);
        //Act
        basket.add(product1, thereIsInventory());
        basket.add(product1, thereIsInventory());
        basket.add(product2, thereIsInventory());
        //Assert
        basketContainsProductWithQuantity(basket, product1, 2);
        basketContainsProductWithQuantity(basket, product2, 1);
    }

    @Test
    public void itDeniesAddingOutOfStockProduct() {
        //Arrange
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct(PRODUCT_1);
        //Act
        thereIsFollowingAmountOfProductAvailable(product1.getId(), 0);

        //Assert
        assertThatThrownBy(() -> basket.add(product1, (productId) -> false))
                .hasMessage("There is not enough product available");
    }

    private Inventory thereIsInventory() {
        return (productId -> true);
    }

    private void thereIsFollowingAmountOfProductAvailable(String productId, int quantity) {

    }

    private void basketContainsProductWithQuantity(Basket basket, Product product1, int expectedQuantity) {
        assertThat(basket.getBasketItems())
                .filteredOn(basketLine -> basketLine.getProductId().equals(product1.getId()))
                .extracting(BasketLine::getQuantity)
                .first()
                .isEqualTo(expectedQuantity);
    }


    private Product thereIsProduct(String description) {
        Product product = new Product(UUID.randomUUID());
        product.setDescription(description);
        return product;

    }
}
