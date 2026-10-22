package com.br.manager.domain.sale.dto;

import com.br.manager.domain.operational.entity.User;

import java.time.LocalDateTime;

public class CommandInputDTO {
    private LocalDateTime dataTime;
    private User user;
    private String status;

    public LocalDateTime getDataTime() { return dataTime; }
    public void setDataTime(LocalDateTime dataTime) { this.dataTime = dataTime; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
