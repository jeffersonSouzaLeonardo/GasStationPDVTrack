package com.br.manager.domain.sale.dto;

import com.br.manager.domain.operational.dto.ProductResponseDTO;
import com.br.manager.domain.price.dto.PriceTableResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class CommandItemResponseDTO {
    private UUID id;
    private CommandResponseDTO command;
    private ProductResponseDTO product;
    private BigDecimal unitValue;
    private BigDecimal discount;
    private PriceTableResponseDTO priceTable;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public CommandResponseDTO getCommand() { return command; }
    public void setCommand(CommandResponseDTO command) { this.command = command; }

    public ProductResponseDTO getProduct() { return product; }
    public void setProduct(ProductResponseDTO product) { this.product = product; }

    public BigDecimal getUnitValue() { return unitValue; }
    public void setUnitValue(BigDecimal unitValue) { this.unitValue = unitValue; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public PriceTableResponseDTO getPriceTable() { return priceTable; }
    public void setPriceTable(PriceTableResponseDTO priceTable) { this.priceTable = priceTable; }
}
