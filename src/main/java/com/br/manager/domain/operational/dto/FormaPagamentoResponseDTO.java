package com.br.manager.domain.operational.dto;

import java.util.UUID;

public class FormaPagamentoResponseDTO {
    private UUID id;
    private String name;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
