package com.br.manager.domain.financial.entity;

import com.br.manager.domain.operational.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cash_item")
public class CashItem {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cash_id", nullable = false)
    private Cash cash;

    @Column(name = "opening_date_time", nullable = false)
    private LocalDateTime openingDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "closing_date_time")
    private LocalDateTime closingDateTime;

    @Column(name = "synchronize", nullable = false)
    private Boolean synchronize = false;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Cash getCash() { return cash; }
    public void setCash(Cash cash) { this.cash = cash; }

    public LocalDateTime getOpeningDateTime() { return openingDateTime; }
    public void setOpeningDateTime(LocalDateTime openingDateTime) { this.openingDateTime = openingDateTime; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getClosingDateTime() { return closingDateTime; }
    public void setClosingDateTime(LocalDateTime closingDateTime) { this.closingDateTime = closingDateTime; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
