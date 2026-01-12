package web.java.model;

import java.time.LocalDateTime;

public class ProductImage {

    private int id;
    private int productId;
    private String image;

    private boolean isPrimary;     // map với is_primary
    private LocalDateTime createdAt;

    public ProductImage() {
    }

    public ProductImage(int id, int productId, String image) {
        this.id = id;
        this.productId = productId;
        this.image = image;
    }

    public ProductImage(int id, int productId, String image,
                        boolean isPrimary, LocalDateTime createdAt) {
        this.id = id;
        this.productId = productId;
        this.image = image;
        this.isPrimary = isPrimary;
        this.createdAt = createdAt;
    }

    /* ===== GETTER / SETTER ===== */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", image='" + image + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
