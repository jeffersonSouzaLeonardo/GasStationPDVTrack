package com.br.manager.domain.financial.dto;

import com.br.manager.domain.sale.entity.Command;
import com.br.manager.domain.financial.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ReceiptInputDTO {
    private Command command;
    private BigDecimal totalValue;
    private BigDecimal discountValue;
    private PaymentMethod paymentMethod;
    private OffsetDateTime dataTime;
    private Boolean synchronize = false;

    public Command getCommand() { return command; }
    public void setCommand(Command command) { this.command = command; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }

    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public OffsetDateTime getDataTime() { return dataTime; }
    public void setDataTime(OffsetDateTime dataTime) { this.dataTime = dataTime; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
