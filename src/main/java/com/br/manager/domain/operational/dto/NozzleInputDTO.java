package com.br.manager.domain.operational.dto;

import com.br.manager.domain.operational.entity.Product;
import com.br.manager.domain.operational.entity.Pump;
import com.br.manager.domain.operational.entity.Tank;

import java.math.BigDecimal;

public class NozzleInputDTO {
    private Pump pump;
    private Tank tank;
    private String code;
    private Product product;
    private BigDecimal meterNumber;
    private Boolean active;

    public Pump getPump() { return pump; }
    public void setPump(Pump pump) { this.pump = pump; }
    public Tank getTank() { return tank; }
    public void setTank(Tank tank) { this.tank = tank; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public BigDecimal getMeterNumber() { return meterNumber; }
    public void setMeterNumber(BigDecimal meterNumber) { this.meterNumber = meterNumber; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
