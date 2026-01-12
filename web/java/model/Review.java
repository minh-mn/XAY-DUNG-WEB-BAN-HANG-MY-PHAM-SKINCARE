package web.java.model;

import java.time.LocalDateTime;

public class Review {

    private int id;

    private int userId;
    private int productId;
    private int orderId;

    private int rating;        // 1–5
    private String comment;
    private String image;

    private boolean status;
    private LocalDateTime createdAt;

    public Review() {
    }

    public Review(int id, int userId, int productId, int orderId,
                  int rating, String comment, String image,
                  boolean status, LocalDateTime createdAt) {

        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.rating = rating;
        this.comment = comment;
        this.image = image;
        this.status = status;
        this.createdAt = createdAt;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
