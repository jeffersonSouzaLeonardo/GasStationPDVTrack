package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "recebimento")
public class Recebimento {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "command_id")
    private UUID commandId;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

    @Column(name = "payment_method_id")
    private UUID paymentMethodId;

    @Column(name = "data_time", nullable = false)
    private OffsetDateTime dataTime;

    @Column(name = "synchronize", nullable = false)
    private Boolean synchronize = false;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

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
