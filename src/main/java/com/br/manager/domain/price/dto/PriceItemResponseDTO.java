package com.br.manager.domain.price.dto;

import com.br.manager.domain.operational.dto.ProductResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class PriceItemResponseDTO {
    private UUID id;
    private PriceTableResponseDTO priceTable;
    private ProductResponseDTO product;
    private BigDecimal unitPrice;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public PriceTableResponseDTO getPriceTable() { return priceTable; }
    public void setPriceTable(PriceTableResponseDTO priceTable) { this.priceTable = priceTable; }
    public ProductResponseDTO getProduct() { return product; }
    public void setProduct(ProductResponseDTO product) { this.product = product; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
