package com.br.manager.domain.financial.dto;

import com.br.manager.domain.financial.entity.Cash;
import com.br.manager.domain.operational.entity.User;

import java.time.OffsetDateTime;

public class CashItemInputDTO {
    private Cash cash;
    private OffsetDateTime openingDateTime;
    private User user;
    private OffsetDateTime closingDateTime;
    private Boolean synchronize = false;

    public Cash getCash() { return cash; }
    public void setCash(Cash cash) { this.cash = cash; }

    public OffsetDateTime getOpeningDateTime() { return openingDateTime; }
    public void setOpeningDateTime(OffsetDateTime openingDateTime) { this.openingDateTime = openingDateTime; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public OffsetDateTime getClosingDateTime() { return closingDateTime; }
    public void setClosingDateTime(OffsetDateTime closingDateTime) { this.closingDateTime = closingDateTime; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
