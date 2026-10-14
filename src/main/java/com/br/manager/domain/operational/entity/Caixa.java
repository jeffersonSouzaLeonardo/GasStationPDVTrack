package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "caixa")
public class Caixa {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "synchronize", nullable = false)
    private Boolean synchronize = false;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getSynchronize() { return synchronize; }
    public void setSynchronize(Boolean synchronize) { this.synchronize = synchronize; }
}
