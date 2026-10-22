-- Flyway migration: create message_error_synchronize table
CREATE TABLE IF NOT EXISTS message_error_synchronize (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    document_synchronize_id CHAR(36),
    message VARCHAR(1000),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_message_error_document_synchronize
        FOREIGN KEY (document_synchronize_id)
        REFERENCES document_sincronize(id)
);

