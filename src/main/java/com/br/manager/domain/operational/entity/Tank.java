package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tanks")
public class Tank {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "capacity_liters", nullable = false)
    private java.math.BigDecimal capacityLiters;

    @Column(name = "dead_stock_liters")
    private java.math.BigDecimal deadStockLiters;

    @Column(name = "current_book_liters", nullable = false)
    private java.math.BigDecimal currentBookLiters;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public java.math.BigDecimal getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(java.math.BigDecimal capacityLiters) { this.capacityLiters = capacityLiters; }
    public java.math.BigDecimal getDeadStockLiters() { return deadStockLiters; }
    public void setDeadStockLiters(java.math.BigDecimal deadStockLiters) { this.deadStockLiters = deadStockLiters; }
    public java.math.BigDecimal getCurrentBookLiters() { return currentBookLiters; }
    public void setCurrentBookLiters(java.math.BigDecimal currentBookLiters) { this.currentBookLiters = currentBookLiters; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
