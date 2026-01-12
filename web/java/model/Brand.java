package web.java.model;

import java.sql.Timestamp;

public class Brand {

    private int id;
    private String name;
    private boolean status;
    private Timestamp createdAt;

    public Brand() {
    }

    /* ===== Constructor cơ bản ===== */
    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /* ===== Constructor đầy đủ (Admin) ===== */
    public Brand(int id, String name, boolean status, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
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
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
