package web.java.model;

import java.time.LocalDateTime;

public class EventProduct {

    private int eventId;
    private int productId;
    private LocalDateTime createdAt;

    public EventProduct() {
    }

    public EventProduct(int eventId, int productId) {
        this.eventId = eventId;
        this.productId = productId;
    }

    public EventProduct(int eventId, int productId, LocalDateTime createdAt) {
        this.eventId = eventId;
        this.productId = productId;
        this.createdAt = createdAt;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "EventProduct{" +
                "eventId=" + eventId +
                ", productId=" + productId +
                '}';
    }
}
