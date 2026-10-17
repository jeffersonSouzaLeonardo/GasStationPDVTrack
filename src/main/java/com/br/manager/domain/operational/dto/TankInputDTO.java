package com.br.manager.domain.operational.dto;

import com.br.manager.domain.operational.entity.Product;

import java.math.BigDecimal;

public class TankInputDTO {
    private String code;
    private Product product;
    private BigDecimal capacityLiters;
    private BigDecimal deadStockLiters;
    private BigDecimal currentBookLiters;
    private Boolean active;

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
