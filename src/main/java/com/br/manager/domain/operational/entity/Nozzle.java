package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "nozzles")
public class Nozzle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "pump_id", nullable = false)
    private UUID pumpId;

    @Column(name = "tank_id", nullable = false)
    private UUID tankId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "meter_number")
    private java.math.BigDecimal meterNumber;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPumpId() { return pumpId; }
    public void setPumpId(UUID pumpId) { this.pumpId = pumpId; }
    public UUID getTankId() { return tankId; }
    public void setTankId(UUID tankId) { this.tankId = tankId; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public java.math.BigDecimal getMeterNumber() { return meterNumber; }
    public void setMeterNumber(java.math.BigDecimal meterNumber) { this.meterNumber = meterNumber; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
