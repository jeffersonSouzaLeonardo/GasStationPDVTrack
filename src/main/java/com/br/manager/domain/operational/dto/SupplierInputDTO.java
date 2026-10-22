package com.br.manager.domain.operational.dto;

import com.br.manager.domain.operational.entity.Pump;
import com.br.manager.domain.operational.entity.Tank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SupplierInputDTO {
    private LocalDateTime dataTime;
    private Tank tank;
    private Pump pump;
    private BigDecimal volume;
    private BigDecimal totalValue;

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
