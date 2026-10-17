package com.br.manager.domain.operational.dto;

import java.util.UUID;

public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private Boolean active;
    private java.time.OffsetDateTime lastLoginAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public java.time.OffsetDateTime getLastLoginAt() { return lastLoginAt; }
    public void setLastLoginAt(java.time.OffsetDateTime lastLoginAt) { this.lastLoginAt = lastLoginAt; }
}
