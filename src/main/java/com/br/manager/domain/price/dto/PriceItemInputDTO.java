package com.br.manager.domain.price.dto;

import com.br.manager.domain.price.entity.PriceTable;
import com.br.manager.domain.operational.entity.Product;

import java.math.BigDecimal;

public class PriceItemInputDTO {
    private PriceTable priceTable;
    private Product product;
    private BigDecimal unitPrice;

    public PriceTable getPriceTable() { return priceTable; }
    public void setPriceTable(PriceTable priceTable) { this.priceTable = priceTable; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
