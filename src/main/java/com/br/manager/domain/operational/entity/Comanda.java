package com.br.manager.domain.operational.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "comanda")
public class Comanda {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "data_time", nullable = false)
    private OffsetDateTime dataTime;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "status", nullable = false)
    private String status;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public OffsetDateTime getDataTime() { return dataTime; }
    public void setDataTime(OffsetDateTime dataTime) { this.dataTime = dataTime; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
