package web.java.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private int id;
    private int userId;
    private String phone;
    private String note;

    private BigDecimal total;
    private int discountPercent;

    private int shippingId;
    private BigDecimal shippingFee;

    private int status; // 0: Pending, 1: Paid, 2: Shipping, 3: Done, 4: Cancel
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(int id, int userId, String phone, String note,
                 BigDecimal total, int discountPercent,
                 int shippingId, BigDecimal shippingFee,
                 int status, LocalDateTime createdAt) {

        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.note = note;
        this.total = total;
        this.discountPercent = discountPercent;
        this.shippingId = shippingId;
        this.shippingFee = shippingFee;
        this.status = status;
        this.createdAt = createdAt;
    }

    /* ===== BUSINESS LOGIC NHẸ ===== */

    public BigDecimal getFinalTotal() {
        BigDecimal result = total.add(shippingFee);
        if (discountPercent > 0) {
            BigDecimal discount = result
                    .multiply(BigDecimal.valueOf(discountPercent))
                    .divide(BigDecimal.valueOf(100));
            result = result.subtract(discount);
        }
        return result;
    }

    public String getFormattedFinalTotal() {
        return String.format("%,.0f", getFinalTotal());
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", finalTotal=" + getFinalTotal() +
                ", status=" + status +
                '}';
    }
}
