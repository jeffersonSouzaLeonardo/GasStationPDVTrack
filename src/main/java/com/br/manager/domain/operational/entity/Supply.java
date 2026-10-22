package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "abastecimento")
public class Supply {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "data_time", nullable = false)
    private LocalDateTime dataTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tank_id", nullable = false)
    private Tank tank;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pump_id", nullable = false)
    private Pump pump;

    @Column(name = "volume", nullable = false)
    private BigDecimal volume;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getDataTime() { return dataTime; }
    public void setDataTime(LocalDateTime dataTime) { this.dataTime = dataTime; }

    public Tank getTank() { return tank; }
    public void setTank(Tank tank) { this.tank = tank; }

    public Pump getPump() { return pump; }
    public void setPump(Pump pump) { this.pump = pump; }

    public BigDecimal getVolume() { return volume; }
    public void setVolume(BigDecimal volume) { this.volume = volume; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
}
