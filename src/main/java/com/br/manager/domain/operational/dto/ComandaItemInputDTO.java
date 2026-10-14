package com.br.manager.domain.operational.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ComandaItemInputDTO {
    private UUID commandId;
    private UUID productId;
    private BigDecimal unitValue;
    private BigDecimal discount;
    private UUID priceTableId;

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
