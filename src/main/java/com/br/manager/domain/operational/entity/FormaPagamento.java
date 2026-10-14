package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "payment_methods")
public class FormaPagamento {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
