-- Create document_sincronize table for synchronization control
CREATE TABLE IF NOT EXISTS document_sincronize (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name_table VARCHAR(255) NOT NULL,
    payload LONGTEXT NOT NULL,
    synchronize BOOLEAN NOT NULL DEFAULT FALSE,
    sincronize_type VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create index on synchronize status for filtering
CREATE INDEX idx_document_sincronize_sync ON document_sincronize(synchronize);

-- Create index on name_table for queries
CREATE INDEX idx_document_sincronize_name_table ON document_sincronize(name_table);
