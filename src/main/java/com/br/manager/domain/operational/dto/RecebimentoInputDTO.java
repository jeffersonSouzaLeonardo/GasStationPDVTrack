package com.br.manager.domain.operational.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class RecebimentoInputDTO {
    private UUID commandId;
    private BigDecimal totalValue;
    private BigDecimal discountValue;
    private UUID paymentMethodId;
    private OffsetDateTime dataTime;
    private Boolean synchronize = false;

    public UUID getCommandId() { return commandId; }
    public void setCommandId(UUID commandId) { this.commandId = commandId; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }

    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }

    public UUID getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(UUID paymentMethodId) { this.paymentMethodId = paymentMethodId; }

    public OffsetDateTime getDataTime() { return dataTime; }
    public void setDataTime(OffsetDateTime dataTime) { this.dataTime = dataTime; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
