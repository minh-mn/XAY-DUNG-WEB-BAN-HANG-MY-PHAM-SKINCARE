package web.java.model;

import java.sql.Timestamp;

public class Category {

    private int id;
    private String name;
    private Integer parentId;
    private Integer collectionId;
    private boolean status;
    private Timestamp createdAt;

    public Category() {
    }

    /* ===== Constructor cơ bản ===== */
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /* ===== Constructor dùng cho menu ===== */
    public Category(int id, String name, Integer parentId, Integer collectionId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.collectionId = collectionId;
    }

    /* ===== Getter / Setter ===== */

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", collectionId=" + collectionId +
                ", status=" + status +
                '}';
    }
}
