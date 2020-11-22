package pl.ksurowka.voucherstore.productcatalog;

import java.util.List;
import java.util.Optional;

public class JDBCProductStorage implements ProductStorage{

    @Override
    public void save(Product newProduct) {

    }

    @Override
    public Optional<Product> getById(String productId) {
        return Optional.empty();
    }

    @Override
    public List<Product> allPublishedProducts() {
        return null;
    }
}
