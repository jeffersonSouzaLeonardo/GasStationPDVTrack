package com.br.manager.domain.operational.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class SupplierResponseDTO {
    private UUID id;
    private OffsetDateTime dataTime;
    private TankResponseDTO tank;
    private PumpResponseDTO pump;
    private BigDecimal volume;
    private BigDecimal totalValue;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public OffsetDateTime getDataTime() { return dataTime; }
    public void setDataTime(OffsetDateTime dataTime) { this.dataTime = dataTime; }

    public TankResponseDTO getTank() { return tank; }
    public void setTank(TankResponseDTO tank) { this.tank = tank; }

    public PumpResponseDTO getPump() { return pump; }
    public void setPump(PumpResponseDTO pump) { this.pump = pump; }

    public BigDecimal getVolume() { return volume; }
    public void setVolume(BigDecimal volume) { this.volume = volume; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
}
