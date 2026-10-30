-- Flyway migration: shift and cash session tables (MySQL 8+)

-- TURNO (Shift)
CREATE TABLE IF NOT EXISTS shift (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    opened_at DATETIME NOT NULL,
    closed_at DATETIME,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- SESSAO DE CAIXA (Cash Session)
CREATE TABLE IF NOT EXISTS cash_session (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    session_number BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    shift_id CHAR(36) NOT NULL,
    opened_by_id CHAR(36) NOT NULL,
    closed_by_id CHAR(36),
    opened_at DATETIME NOT NULL,
    closed_at DATETIME,
    opening_amount DECIMAL(19,4) NOT NULL DEFAULT 0,
    closing_amount DECIMAL(19,4),
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    synchronize BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_cash_session_shift FOREIGN KEY (shift_id) REFERENCES shift(id),
    CONSTRAINT fk_cash_session_opened_by FOREIGN KEY (opened_by_id) REFERENCES users(id),
    CONSTRAINT fk_cash_session_closed_by FOREIGN KEY (closed_by_id) REFERENCES users(id)
);

CREATE INDEX idx_cash_session_shift ON cash_session(shift_id);
CREATE INDEX idx_cash_session_opened_by ON cash_session(opened_by_id);
CREATE INDEX idx_cash_session_status ON cash_session(status);
