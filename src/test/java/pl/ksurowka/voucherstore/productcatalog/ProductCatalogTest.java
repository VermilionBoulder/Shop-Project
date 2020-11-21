package pl.ksurowka.voucherstore.productcatalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

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
    public void AllowsListingAllPublishedProducts() {
        ProductCatalogFacade api = thereIsProductCatalog();
        String draftProductId = api.createProduct();
        String productId = api.createProduct();
        api.updateProductDetails(productId, MY_DESCRIPTION);
        api.applyPrice(productId, BigDecimal.TEN);

        List<Product> products = api.allPublishedProducts();

        Assert.assertEquals(1, products.size());
    }

    private static ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogFacade();
    }
}
