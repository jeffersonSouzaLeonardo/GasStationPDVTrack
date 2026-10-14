package com.br.manager.domain.operational.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PriceItemInputDTO {
    private UUID priceTableId;
    private UUID productId;
    private BigDecimal unitPrice;

    public UUID getPriceTableId() { return priceTableId; }
    public void setPriceTableId(UUID priceTableId) { this.priceTableId = priceTableId; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
