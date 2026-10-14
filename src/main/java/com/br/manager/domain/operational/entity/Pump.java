package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pumps")
public class Pump {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "location")
    private String location;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
