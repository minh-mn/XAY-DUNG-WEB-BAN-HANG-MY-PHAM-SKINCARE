package web.java.model;

import java.math.BigDecimal;

public class OrderItem {

    private int id;
    private int orderId;
    private int productId;

    private BigDecimal price; // snapshot price
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(int id, int orderId, int productId,
                     BigDecimal price, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    /* ===== BUSINESS LOGIC NHẸ ===== */

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public String getFormattedTotalPrice() {
        return String.format("%,.0f", getTotalPrice());
    }

    /* ===== GETTER / SETTER ===== */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
