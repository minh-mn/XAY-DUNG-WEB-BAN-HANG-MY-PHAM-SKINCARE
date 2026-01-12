package web.java.model;

import java.time.LocalDateTime;

public class Coupon {

    private int id;
    private String code;

    private int discountPercent; // 1–100

    private int quantity;        // tổng lượt
    private int used;            // đã dùng

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private boolean status;
    private LocalDateTime createdAt;

    public Coupon() {
    }

    public Coupon(int id, String code, int discountPercent,
                  int quantity, int used,
                  LocalDateTime startDate, LocalDateTime endDate,
                  boolean status, LocalDateTime createdAt) {

        this.id = id;
        this.code = code;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.used = used;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    /* ===== BUSINESS LOGIC NHẸ ===== */

    public boolean isAvailable() {
        return status && used < quantity;
    }

    /* ===== GETTER / SETTER ===== */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
        return "Coupon{" +
                "code='" + code + '\'' +
                ", discountPercent=" + discountPercent +
                ", used=" + used +
                "/" + quantity +
                '}';
    }
}
