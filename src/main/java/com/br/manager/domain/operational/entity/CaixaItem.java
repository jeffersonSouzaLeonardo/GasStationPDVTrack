package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "caixa_item")
public class CaixaItem {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "cash_register_id", nullable = false)
    private UUID cashRegisterId;

    @Column(name = "opening_date_time", nullable = false)
    private OffsetDateTime openingDateTime;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "closing_date_time")
    private OffsetDateTime closingDateTime;

    @Column(name = "synchronize", nullable = false)
    private Boolean synchronize = false;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

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
