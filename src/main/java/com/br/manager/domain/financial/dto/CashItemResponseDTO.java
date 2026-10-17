package com.br.manager.domain.financial.dto;

import com.br.manager.domain.operational.dto.UserResponseDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CashItemResponseDTO {
    private UUID id;
    private CashResponseDTO cashRegister;
    private OffsetDateTime openingDateTime;
    private UserResponseDTO user;
    private OffsetDateTime closingDateTime;
    private Boolean synchronize;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public CashResponseDTO getCashRegister() { return cashRegister; }
    public void setCashRegister(CashResponseDTO cashRegister) { this.cashRegister = cashRegister; }

    public OffsetDateTime getOpeningDateTime() { return openingDateTime; }
    public void setOpeningDateTime(OffsetDateTime openingDateTime) { this.openingDateTime = openingDateTime; }

    public UserResponseDTO getUser() { return user; }
    public void setUser(UserResponseDTO user) { this.user = user; }

    public OffsetDateTime getClosingDateTime() { return closingDateTime; }
    public void setClosingDateTime(OffsetDateTime closingDateTime) { this.closingDateTime = closingDateTime; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
