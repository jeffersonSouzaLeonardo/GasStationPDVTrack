package com.br.manager.domain.sale.dto;

import com.br.manager.domain.sale.entity.Command;
import com.br.manager.domain.price.entity.PriceTable;
import com.br.manager.domain.operational.entity.Product;

import java.math.BigDecimal;

public class CommandItemInputDTO {
    private Command command;
    private Product product;
    private BigDecimal unitValue;
    private BigDecimal discount;
    private PriceTable priceTable;

    public Command getCommand() { return command; }
    public void setCommand(Command command) { this.command = command; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public BigDecimal getUnitValue() { return unitValue; }
    public void setUnitValue(BigDecimal unitValue) { this.unitValue = unitValue; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public PriceTable getPriceTable() { return priceTable; }
    public void setPriceTable(PriceTable priceTable) { this.priceTable = priceTable; }
}
