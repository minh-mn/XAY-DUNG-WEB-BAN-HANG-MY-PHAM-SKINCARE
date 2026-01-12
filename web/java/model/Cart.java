package web.java.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int id;
    private int userId;
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public Cart(int userId) {
        this.userId = userId;
        this.cartItems = new ArrayList<>();
    }

    /* ===== BUSINESS LOGIC ĐƠN GIẢN ===== */

    public void addItem(CartItem newItem, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == newItem.getProduct().getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        newItem.setQuantity(quantity);
        cartItems.add(newItem);
    }

    public void removeItemByProductId(int productId) {
        cartItems.removeIf(item ->
            item.getProduct().getId() == productId
        );
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            total = total.add(item.getTotalPrice());
        }
        return total;
    }

    public BigDecimal getTotalWithDiscount(BigDecimal shippingFee, int discountPercent) {
        BigDecimal total = getTotal().add(shippingFee);

        BigDecimal discount = total
                .multiply(BigDecimal.valueOf(discountPercent))
                .divide(BigDecimal.valueOf(100));

        return total.subtract(discount);
    }

    public String getFormattedTotal() {
        return String.format("%,.0f", getTotal());
    }

    /* ===== GETTER / SETTER ===== */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalDiscount(double shippingFee, double discountPercent) {
        BigDecimal total = getTotal()
                .add(BigDecimal.valueOf(shippingFee));

        BigDecimal discount = total
                .multiply(BigDecimal.valueOf(discountPercent))
                .divide(BigDecimal.valueOf(100));

        return total.subtract(discount).doubleValue();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", cartItems=" + cartItems +
                '}';
    }
}
