package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "capacity_liters", nullable = false)
    private BigDecimal capacityLiters;

    @Column(name = "dead_stock_liters")
    private BigDecimal deadStockLiters;

    @Column(name = "current_book_liters", nullable = false)
    private BigDecimal currentBookLiters;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public BigDecimal getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(BigDecimal capacityLiters) { this.capacityLiters = capacityLiters; }
    public BigDecimal getDeadStockLiters() { return deadStockLiters; }
    public void setDeadStockLiters(BigDecimal deadStockLiters) { this.deadStockLiters = deadStockLiters; }
    public BigDecimal getCurrentBookLiters() { return currentBookLiters; }
    public void setCurrentBookLiters(BigDecimal currentBookLiters) { this.currentBookLiters = currentBookLiters; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
