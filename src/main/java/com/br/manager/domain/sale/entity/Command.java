package com.br.manager.domain.sale.entity;

import com.br.manager.domain.financial.entity.Cash;
import com.br.manager.domain.price.entity.PriceTable;
import com.br.manager.domain.operational.entity.User;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "comanda")
public class Command {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "data_time", nullable = false)
    private OffsetDateTime dataTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cash_id", nullable = false)
    private Cash cash;

    public Cash getCash() { return cash; }
    public void setCash(Cash cash) { this.cash = cash; }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_table_id", nullable = false)
    private PriceTable priceTable;

    public PriceTable getPriceTable() { return priceTable; }
    public void setPriceTable(PriceTable priceTable) { this.priceTable = priceTable; }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public OffsetDateTime getDataTime() { return dataTime; }
    public void setDataTime(OffsetDateTime dataTime) { this.dataTime = dataTime; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
