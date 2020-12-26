package pl.ksurowka.voucherstore.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ProductCatalogFacade {

    ConcurrentHashMap<String, Product> products;
    HashMapProductStorage productStorage;

    public ProductCatalogFacade(ProductStorage productStorage) {
        this.productStorage = new HashMapProductStorage();
    }

    public String createProduct() {
        Product newProduct = new Product(UUID.randomUUID());
        productStorage.save(newProduct);

        return newProduct.getId();
    }

    public boolean isExists(String productId) {
        return productStorage.getById(productId).isPresent();
    }

    public Product getById(String productId) {
        return  getProductOrException(productId);
    }


    public void updateProductDetails(String productId, String myDescription, String myPicture) {
        Product product = getProductOrException(productId);

        product.setDescription(myDescription);
        product.setPicture(myPicture);
    }

    public void applyPrice(String productId, BigDecimal price) {
        Product loaded = getProductOrException(productId);

        loaded.setPrice(price);
    }

    public void updateProductDetails(String productId, String myDescription) {
        Product loaded = getProductOrException(productId);

        if (loaded == null) {
            throw new ProductNotFoundException(String.format("There is no product with id: %s", productId));
        }

        loaded.setDescription(myDescription);
    }

    public List<Product> allPublishedProducts() {
        return productStorage.allPublishedProducts();
    }

    private Product getProductOrException(String productId) {
        return productStorage.getById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("There is no product with id: %s", productId)));
    }
}
