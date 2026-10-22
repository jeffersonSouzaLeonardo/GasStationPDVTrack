package com.br.manager.domain.sale.dto;

import com.br.manager.domain.operational.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommandResponseDTO {
    private UUID id;
    private LocalDateTime dataTime;
    private UserResponseDTO user;
    private String status;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getDataTime() { return dataTime; }
    public void setDataTime(LocalDateTime dataTime) { this.dataTime = dataTime; }

    public UserResponseDTO getUser() { return user; }
    public void setUser(UserResponseDTO user) { this.user = user; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
