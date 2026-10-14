package com.br.manager.domain.operational.dto;

public class CaixaInputDTO {
    private String name;
    private Boolean synchronize = false;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
