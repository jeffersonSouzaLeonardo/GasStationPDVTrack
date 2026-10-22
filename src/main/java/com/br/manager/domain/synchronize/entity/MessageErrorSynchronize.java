package com.br.manager.domain.synchronize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "message_error_synchronize")
public class MessageErrorSynchronize {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_synchronize_id", nullable = true)
    private DocumentSynchronize documentSynchronize;

    @NotBlank
    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DocumentSynchronize getDocumentSynchronize() {
        return documentSynchronize;
    }

    public void setDocumentSynchronize(DocumentSynchronize documentSynchronize) {
        this.documentSynchronize = documentSynchronize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
