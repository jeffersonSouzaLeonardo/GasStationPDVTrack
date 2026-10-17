-- Flyway migration for PDV (MySQL 8+)
-- Creates core tables replicated from retaguarda and PDV-specific tables

-- USERS
CREATE TABLE IF NOT EXISTS users (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    last_login_at DATETIME,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

-- PRODUCTS
CREATE TABLE IF NOT EXISTS products (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    sku VARCHAR(100) NOT NULL,
    name VARCHAR(255) NOT NULL,
    product_type VARCHAR(50) NOT NULL,
    unit VARCHAR(10) NOT NULL,
    anp_code VARCHAR(20),
    ncm VARCHAR(10),
    cest VARCHAR(10),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- PRICE TABLES AND ITEMS
CREATE TABLE IF NOT EXISTS price_tables (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL,
    station_id CHAR(36),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS price_items (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    price_table_id CHAR(36) NOT NULL,
    product_id CHAR(36) NOT NULL,
    unit_price DECIMAL(19,4) NOT NULL DEFAULT 0,
    CONSTRAINT fk_price_items_price_table FOREIGN KEY (price_table_id) REFERENCES price_tables(id),
    CONSTRAINT fk_price_items_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- TANKS
CREATE TABLE IF NOT EXISTS tanks (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL,
    capacity DECIMAL(19,4) NOT NULL,
    current_volume DECIMAL(19,4) NOT NULL DEFAULT 0,
    product_id CHAR(36) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_tanks_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- PUMPS
CREATE TABLE IF NOT EXISTS pumps (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    pump_number VARCHAR(50) NOT NULL,
    tank_id CHAR(36) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_pumps_tank FOREIGN KEY (tank_id) REFERENCES tanks(id)
);

-- NOZZLES
CREATE TABLE IF NOT EXISTS nozzles (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    nozzle_number VARCHAR(50) NOT NULL,
    pump_id CHAR(36) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_nozzles_pump FOREIGN KEY (pump_id) REFERENCES pumps(id)
);

-- PAYMENT METHODS
CREATE TABLE IF NOT EXISTS payment_methods (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL
);

-- PDV-SPECIFIC TABLES

-- COMANDA (Command/Order)
CREATE TABLE IF NOT EXISTS command (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    data_time DATETIME NOT NULL,
    user_id CHAR(36) NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_command_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- RECEBIMENTO (Receipt)
CREATE TABLE IF NOT EXISTS receipt (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    command_id CHAR(36),
    total_value DECIMAL(19,4) NOT NULL,
    discount_value DECIMAL(19,4) NOT NULL DEFAULT 0,
    payment_method_id CHAR(36),
    data_time DATETIME NOT NULL,
    synchronize BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_receipt_command FOREIGN KEY (command_id) REFERENCES command(id),
    CONSTRAINT fk_receipt_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)
);

-- CAIXA (Cash Register)
CREATE TABLE IF NOT EXISTS cash (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL,
    synchronize BOOLEAN NOT NULL DEFAULT FALSE
);

-- CAIXA_ITEM (Cash Register Item)
CREATE TABLE IF NOT EXISTS cash_item (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    cash_register_id CHAR(36) NOT NULL,
    opening_date_time DATETIME NOT NULL,
    user_id CHAR(36) NOT NULL,
    closing_date_time DATETIME,
    synchronize BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_cash_item_cash_register FOREIGN KEY (cash_register_id) REFERENCES cash(id),
    CONSTRAINT fk_cash_item_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- COMANDA_ITEM (Order Item)
CREATE TABLE IF NOT EXISTS command_item (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    command_id CHAR(36) NOT NULL,
    product_id CHAR(36) NOT NULL,
    unit_value DECIMAL(19,4) NOT NULL,
    discount DECIMAL(19,4) NOT NULL DEFAULT 0,
    price_table_id CHAR(36) NOT NULL,
    CONSTRAINT fk_command_item_command FOREIGN KEY (command_id) REFERENCES command(id),
    CONSTRAINT fk_command_item_product FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_command_item_price_table FOREIGN KEY (price_table_id) REFERENCES price_tables(id)
);

-- ABASTECIMENTO (Fuel Supply)
CREATE TABLE IF NOT EXISTS supply (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    data_time DATETIME NOT NULL,
    tank_id CHAR(36) NOT NULL,
    pump_id CHAR(36) NOT NULL,
    volume DECIMAL(19,4) NOT NULL,
    total_value DECIMAL(19,4) NOT NULL,
    CONSTRAINT fk_supply_tank FOREIGN KEY (tank_id) REFERENCES tanks(id),
    CONSTRAINT fk_abastecimento_pump FOREIGN KEY (pump_id) REFERENCES pumps(id)
);

-- INDEXES
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_products_sku ON products(sku);
CREATE INDEX idx_price_items_price_table ON price_items(price_table_id);
CREATE INDEX idx_tanks_product ON tanks(product_id);
CREATE INDEX idx_pumps_tank ON pumps(tank_id);
CREATE INDEX idx_nozzles_pump ON nozzles(pump_id);
CREATE INDEX idx_cash_item_cash_register ON cash_item(cash_register_id);
CREATE INDEX idx_cash_item_user ON cash_item(user_id);
CREATE INDEX idx_command_user ON command(user_id);
CREATE INDEX idx_command_item_command ON command_item(command_id);
CREATE INDEX idx_command_item_product ON command_item(product_id);
CREATE INDEX idx_supply_tank ON supply(tank_id);
CREATE INDEX idx_supply_pump ON supply(pump_id);
