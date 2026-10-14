package com.br.manager.domain.operational.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ComandaResponseDTO {
    private UUID id;
    private OffsetDateTime dataTime;
    private UUID userId;
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
