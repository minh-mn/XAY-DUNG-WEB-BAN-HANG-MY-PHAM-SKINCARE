package web.java.model;

import java.math.BigDecimal;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /* ===== TÍNH TIỀN (CHUẨN BACKEND) ===== */
    public BigDecimal getTotalPrice() {
        if (product == null || product.getPrice() == null) {
            return BigDecimal.ZERO;
        }
        return product.getPrice()
                      .multiply(BigDecimal.valueOf(quantity));
    }

    public String getFormattedTotalPrice() {
        return String.format("%,.0f", getTotalPrice());
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
