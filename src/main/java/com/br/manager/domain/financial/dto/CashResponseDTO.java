package com.br.manager.domain.financial.dto;

import java.util.UUID;

public class CashResponseDTO {
    private UUID id;
    private String name;
    private Boolean synchronize;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
