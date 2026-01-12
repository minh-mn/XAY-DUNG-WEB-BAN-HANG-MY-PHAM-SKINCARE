package web.java.model;

import java.time.LocalDateTime;

public class Address {

    private int id;
    private int userId;
    private String address;

    private boolean isDefault;
    private LocalDateTime createdAt;

    public Address() {
    }

    public Address(int id, int userId, String address) {
        this.id = id;
        this.userId = userId;
        this.address = address;
    }

    public Address(int id, int userId, String address,
                   boolean isDefault, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.isDefault = isDefault;
        this.createdAt = createdAt;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
