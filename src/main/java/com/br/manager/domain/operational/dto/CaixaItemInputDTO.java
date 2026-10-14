package com.br.manager.domain.operational.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CaixaItemInputDTO {
    private UUID cashRegisterId;
    private OffsetDateTime openingDateTime;
    private UUID userId;
    private OffsetDateTime closingDateTime;
    private Boolean synchronize = false;

    public UUID getCashRegisterId() { return cashRegisterId; }
    public void setCashRegisterId(UUID cashRegisterId) { this.cashRegisterId = cashRegisterId; }

    public OffsetDateTime getOpeningDateTime() { return openingDateTime; }
    public void setOpeningDateTime(OffsetDateTime openingDateTime) { this.openingDateTime = openingDateTime; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public OffsetDateTime getClosingDateTime() { return closingDateTime; }
    public void setClosingDateTime(OffsetDateTime closingDateTime) { this.closingDateTime = closingDateTime; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
