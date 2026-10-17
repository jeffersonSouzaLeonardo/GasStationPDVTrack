package com.br.manager.domain.financial.dto;

public class CashInputDTO {
    private String name;
    private Boolean synchronize = false;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
