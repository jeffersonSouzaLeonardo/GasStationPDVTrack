package com.br.manager.domain.operational.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class NozzleResponseDTO {
    private UUID id;
    private PumpResponseDTO pump;
    private TankResponseDTO tank;
    private String code;
    private ProductResponseDTO product;
    private BigDecimal meterNumber;
    private Boolean active;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public PumpResponseDTO getPump() { return pump; }
    public void setPump(PumpResponseDTO pump) { this.pump = pump; }
    public TankResponseDTO getTank() { return tank; }
    public void setTank(TankResponseDTO tank) { this.tank = tank; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public ProductResponseDTO getProduct() { return product; }
    public void setProduct(ProductResponseDTO product) { this.product = product; }
    public BigDecimal getMeterNumber() { return meterNumber; }
    public void setMeterNumber(BigDecimal meterNumber) { this.meterNumber = meterNumber; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
