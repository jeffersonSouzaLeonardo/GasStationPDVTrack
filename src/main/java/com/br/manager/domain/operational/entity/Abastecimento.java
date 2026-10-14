package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "abastecimento")
public class Abastecimento {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "data_time", nullable = false)
    private OffsetDateTime dataTime;

    @Column(name = "tank_id", nullable = false)
    private UUID tankId;

    @Column(name = "pump_id", nullable = false)
    private UUID pumpId;

    @Column(name = "volume", nullable = false)
    private BigDecimal volume;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public OffsetDateTime getDataTime() { return dataTime; }
    public void setDataTime(OffsetDateTime dataTime) { this.dataTime = dataTime; }

    public UUID getTankId() { return tankId; }
    public void setTankId(UUID tankId) { this.tankId = tankId; }

    public UUID getPumpId() { return pumpId; }
    public void setPumpId(UUID pumpId) { this.pumpId = pumpId; }

    public BigDecimal getVolume() { return volume; }
    public void setVolume(BigDecimal volume) { this.volume = volume; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
}
