package pl.ksurowka.voucherstore.sales;

public interface Inventory {
    boolean isAvailable(String productId);
}
