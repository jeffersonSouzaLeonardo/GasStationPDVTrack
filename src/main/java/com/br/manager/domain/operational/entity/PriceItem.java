package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "price_items")
public class PriceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "price_table_id", nullable = false)
    private UUID priceTableId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPriceTableId() { return priceTableId; }
    public void setPriceTableId(UUID priceTableId) { this.priceTableId = priceTableId; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
