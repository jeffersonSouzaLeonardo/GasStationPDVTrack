package com.br.manager.domain.operational.dto;

import java.util.UUID;

public class UserRequestDTO {
    private UUID id;
    private String name;
    private String email;
    private String login;
    private String role;
    private String passwordHash;
    private String phone;
    private Boolean active;
    private java.time.OffsetDateTime lastLoginAt;

    public UserRequestDTO() {
    }

    public UserRequestDTO(UUID id, String name, String email, String login, String role, String passwordHash, String phone, Boolean active, java.time.OffsetDateTime lastLoginAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.role = role;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.active = active;
        this.lastLoginAt = lastLoginAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public java.time.OffsetDateTime getLastLoginAt() { return lastLoginAt; }
    public void setLastLoginAt(java.time.OffsetDateTime lastLoginAt) { this.lastLoginAt = lastLoginAt; }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}