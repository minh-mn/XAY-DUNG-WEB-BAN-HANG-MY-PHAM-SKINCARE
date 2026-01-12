package web.java.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transport {

    private int id;
    private String name;
    private BigDecimal fee;

    private boolean status;
    private LocalDateTime createdAt;

    public Transport() {
    }

    public Transport(int id, String name, BigDecimal fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
    }

    public Transport(int id, String name, BigDecimal fee,
                     boolean status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.status = status;
        this.createdAt = createdAt;
    }

    /* ===== GETTER / SETTER ===== */

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public boolean isStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
