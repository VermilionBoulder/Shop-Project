package pl.ksurowka.voucherstore.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private String productId;
    private String description;
    private String picture;
    private BigDecimal price;

    Product() {}

    public Product(UUID productId) {
        this.productId = productId.toString();
    }
    public String getId() {
        return productId.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
