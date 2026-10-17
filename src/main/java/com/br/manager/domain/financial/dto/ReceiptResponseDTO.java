package com.br.manager.domain.financial.dto;

import com.br.manager.domain.sale.dto.CommandResponseDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ReceiptResponseDTO {
    private UUID id;
    private CommandResponseDTO command;
    private BigDecimal totalValue;
    private BigDecimal discountValue;
    private PaymentMethodResponseDTO paymentMethod;
    private OffsetDateTime dataTime;
    private Boolean synchronize;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public CommandResponseDTO getCommand() { return command; }
    public void setCommand(CommandResponseDTO command) { this.command = command; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }

    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }

    public PaymentMethodResponseDTO getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethodResponseDTO paymentMethod) { this.paymentMethod = paymentMethod; }

    public OffsetDateTime getDataTime() { return dataTime; }
    public void setDataTime(OffsetDateTime dataTime) { this.dataTime = dataTime; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
