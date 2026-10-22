package com.br.manager.domain.synchronize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "document_sincronize")
public class DocumentSynchronize {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome da tabela é obrigatório.")
    @Column(name = "name_table", nullable = false, updatable = false)
    private String nameTable;

    @NotBlank(message = "O payload é obrigatório.")
    @Column(name = "payload", nullable = false, columnDefinition = "text")
    private String payload;

    private Boolean synchronize = false;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    @NotNull(message = "O tipo da sincronização é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(name = "sincronize_type", nullable = false)
    private SynchronizeTypeENUM synchronizeTypeENUM;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Boolean getSynchronize() {
        return synchronize;
    }

    public void setSynchronize(Boolean synchronize) {
        this.synchronize = synchronize;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SynchronizeTypeENUM getSincronizeTypeENUM() {
        return synchronizeTypeENUM;
    }

    public void setSincronizeTypeENUM(SynchronizeTypeENUM synchronizeTypeENUM) {
        this.synchronizeTypeENUM = synchronizeTypeENUM;
    }
}
