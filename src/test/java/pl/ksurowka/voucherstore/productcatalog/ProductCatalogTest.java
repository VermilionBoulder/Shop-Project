package pl.ksurowka.voucherstore.productcatalog;

import org.junit.Assert;
import org.junit.Test;
import pl.ksurowka.voucherstore.productcatalog.Product;
import pl.ksurowka.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.ksurowka.voucherstore.productcatalog.ProductCatalogFacade;
import pl.ksurowka.voucherstore.productcatalog.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductCatalogTest {

    public static final String MY_DESCRIPTION = "My Fancy Product";
    public static final String MY_PICTURE = "http://my_image.jpeg";

    @Test
    public void AllowsCreatingProduct() {
        //Arrange
        ProductCatalogFacade api = thereIsProductCatalog();

        //Act
        String productId = api.createProduct();

        //Assert
        Assert.assertTrue(api.isExists(productId));
    }

    @Test
    public void AllowsCheckingExistence() {
        //Arrange
        ProductCatalogFacade api = thereIsProductCatalog();

        //Act
        String productId = api.createProduct();
        Product loaded = api.getById(productId);

        //Assert
        Assert.assertEquals(productId, loaded.getId());
    }

    @Test
    public void AllowsSettingProductDetails() {
        //Arrange
        ProductCatalogFacade api = thereIsProductCatalog();
        String productId = api.createProduct();

        //Act
        api.updateProductDetails(productId, MY_DESCRIPTION, MY_PICTURE);
        Product loaded = api.getById(productId);

        //Assert
        Assert.assertEquals("My Fancy Product", loaded.getDescription());
    }

    @Test
    public void AllowsApplyingPrice() {
        //Arrange
        ProductCatalogFacade api = thereIsProductCatalog();
        String productId = api.createProduct();

        //Act
        api.applyPrice(productId, BigDecimal.TEN);
        Product loaded = api.getById(productId);

        //Assert
        Assert.assertEquals(BigDecimal.TEN, loaded.getPrice());
    }

    @Test
    public void DeniesActingOnNonexistentProductV1() {
        try {
            ProductCatalogFacade api = thereIsProductCatalog();
            api.applyPrice("doesNotExist", BigDecimal.valueOf(20));
            Assert.fail("Should throw exception");
        } catch (ProductNotFoundException e) {
            Assert.assertTrue(true);
        }

    }

    @Test(expected = ProductNotFoundException.class)
    public void DeniesActingOnNonexistentProductV2() {
        ProductCatalogFacade api = thereIsProductCatalog();
        api.applyPrice("doesNotExist", BigDecimal.valueOf(20));
        api.updateProductDetails("doesNotExist", "desc", "img");
    }

    @Test
    public void DeniesActingOnNonexistentProductV3() {
        ProductCatalogFacade api = thereIsProductCatalog();

        assertThatThrownBy(() -> api.applyPrice("doesNotExist", BigDecimal.valueOf(20)))
                .hasMessage("There is no product with id: doesNotExist");

        assertThatThrownBy(() -> api.updateProductDetails("doesNotExist", "desc", "img"))
                .hasMessage("There is no product with id: doesNotExist");

        assertThatThrownBy(() -> api.getById("doesNotExist"))
                .hasMessage("There is no product with id: doesNotExist");
    }

    @Test
    public void AllowsListingAllPublishedProducts() {
        ProductCatalogFacade api = thereIsProductCatalog();
        String draftProductId = api.createProduct();
        String productId = api.createProduct();
        api.updateProductDetails(productId, MY_DESCRIPTION);
        api.applyPrice(productId, BigDecimal.TEN);

        List<Product> products = api.allPublishedProducts();

        assertThat(products)
                .hasSize(1)
                .extracting(Product::getId)
                .contains(productId)
                .doesNotContain(draftProductId);
    }

    private static ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogConfiguration().productCatalogFacade();
    }
}
