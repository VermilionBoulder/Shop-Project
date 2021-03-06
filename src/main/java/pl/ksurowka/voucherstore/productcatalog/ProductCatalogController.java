package pl.ksurowka.voucherstore.productcatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCatalogController {

    @Autowired
    ProductCatalogFacade productCatalogFacade;

    @GetMapping("/api/products")
    public List<Product> my_products() {
        return productCatalogFacade.allPublishedProducts();
    }
}
