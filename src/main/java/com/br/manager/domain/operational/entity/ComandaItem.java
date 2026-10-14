package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "comanda_item")
public class ComandaItem {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "command_id", nullable = false)
    private UUID commandId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "unit_value", nullable = false)
    private BigDecimal unitValue;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @Column(name = "price_table_id", nullable = false)
    private UUID priceTableId;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getCommandId() { return commandId; }
    public void setCommandId(UUID commandId) { this.commandId = commandId; }

    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }

    public BigDecimal getUnitValue() { return unitValue; }
    public void setUnitValue(BigDecimal unitValue) { this.unitValue = unitValue; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public UUID getPriceTableId() { return priceTableId; }
    public void setPriceTableId(UUID priceTableId) { this.priceTableId = priceTableId; }
}
