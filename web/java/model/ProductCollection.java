package web.java.model;

import java.time.LocalDateTime;

public class ProductCollection {

    private int id;
    private String name;
    private String description;

    private boolean status;
    private LocalDateTime createdAt;

    public ProductCollection() {
    }

    public ProductCollection(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCollection(int id, String name,
                             String description,
                             boolean status,
                             LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "ProductCollection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
