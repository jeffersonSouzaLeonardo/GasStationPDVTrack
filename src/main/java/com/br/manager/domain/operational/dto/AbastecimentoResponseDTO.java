package com.br.manager.domain.operational.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class AbastecimentoResponseDTO {
    private UUID id;
    private OffsetDateTime dataTime;
    private UUID tankId;
    private UUID pumpId;
    private BigDecimal volume;
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
