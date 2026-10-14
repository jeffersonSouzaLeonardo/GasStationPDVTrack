-- Flyway migration for PDV (PostgreSQL)
-- Creates core tables replicated from retaguarda and PDV-specific tables
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- USERS
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    last_login_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ
);

-- PRODUCTS
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sku VARCHAR(100) NOT NULL,
    name VARCHAR(255) NOT NULL,
    product_type VARCHAR(50) NOT NULL,
    unit VARCHAR(10) NOT NULL,
    anp_code VARCHAR(20),
    ncm VARCHAR(10),
    cest VARCHAR(10),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- PRICE TABLES AND ITEMS
CREATE TABLE IF NOT EXISTS price_tables (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    station_id UUID,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS price_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    price_table_id UUID NOT NULL,
    product_id UUID NOT NULL,
    unit_price NUMERIC(19,4) NOT NULL DEFAULT 0,
    CONSTRAINT fk_price_items_price_table FOREIGN KEY (price_table_id) REFERENCES price_tables(id),
    CONSTRAINT fk_price_items_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- TANKS
CREATE TABLE IF NOT EXISTS tanks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    capacity NUMERIC(19,4) NOT NULL,
    current_volume NUMERIC(19,4) NOT NULL DEFAULT 0,
    product_id UUID NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_tanks_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- PUMPS
CREATE TABLE IF NOT EXISTS pumps (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    pump_number VARCHAR(50) NOT NULL,
    tank_id UUID NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_pumps_tank FOREIGN KEY (tank_id) REFERENCES tanks(id)
);

-- NOZZLES
CREATE TABLE IF NOT EXISTS nozzles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nozzle_number VARCHAR(50) NOT NULL,
    pump_id UUID NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_nozzles_pump FOREIGN KEY (pump_id) REFERENCES pumps(id)
);

-- PAYMENT METHODS
CREATE TABLE IF NOT EXISTS payment_methods (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL
);

-- PDV-SPECIFIC TABLES

-- RECEBIMENTO (Receipt)
CREATE TABLE IF NOT EXISTS recebimento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    command_id UUID,
    total_value NUMERIC(19,4) NOT NULL,
    discount_value NUMERIC(19,4) NOT NULL DEFAULT 0,
    payment_method_id UUID,
    data_time TIMESTAMPTZ NOT NULL,
    synchronize BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_recebimento_command FOREIGN KEY (command_id) REFERENCES comanda(id),
    CONSTRAINT fk_recebimento_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)
);

-- CAIXA (Cash Register)
CREATE TABLE IF NOT EXISTS caixa (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    synchronize BOOLEAN NOT NULL DEFAULT FALSE
);

-- CAIXA_ITEM (Cash Register Item)
CREATE TABLE IF NOT EXISTS caixa_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cash_register_id UUID NOT NULL,
    opening_date_time TIMESTAMPTZ NOT NULL,
    user_id UUID NOT NULL,
    closing_date_time TIMESTAMPTZ,
    synchronize BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_caixa_item_cash_register FOREIGN KEY (cash_register_id) REFERENCES caixa(id),
    CONSTRAINT fk_caixa_item_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- COMANDA (Command/Order)
CREATE TABLE IF NOT EXISTS comanda (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    data_time TIMESTAMPTZ NOT NULL,
    user_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_comanda_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- COMANDA_ITEM (Order Item)
CREATE TABLE IF NOT EXISTS comanda_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    command_id UUID NOT NULL,
    product_id UUID NOT NULL,
    unit_value NUMERIC(19,4) NOT NULL,
    discount NUMERIC(19,4) NOT NULL DEFAULT 0,
    price_table_id UUID NOT NULL,
    CONSTRAINT fk_comanda_item_command FOREIGN KEY (command_id) REFERENCES comanda(id),
    CONSTRAINT fk_comanda_item_product FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_comanda_item_price_table FOREIGN KEY (price_table_id) REFERENCES price_tables(id)
);

-- ABASTECIMENTO (Fuel Supply)
CREATE TABLE IF NOT EXISTS abastecimento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    data_time TIMESTAMPTZ NOT NULL,
    tank_id UUID NOT NULL,
    pump_id UUID NOT NULL,
    volume NUMERIC(19,4) NOT NULL,
    total_value NUMERIC(19,4) NOT NULL,
    CONSTRAINT fk_abastecimento_tank FOREIGN KEY (tank_id) REFERENCES tanks(id),
    CONSTRAINT fk_abastecimento_pump FOREIGN KEY (pump_id) REFERENCES pumps(id)
);

-- INDEXES
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_products_sku ON products(sku);
CREATE INDEX IF NOT EXISTS idx_price_items_price_table ON price_items(price_table_id);
CREATE INDEX IF NOT EXISTS idx_tanks_product ON tanks(product_id);
CREATE INDEX IF NOT EXISTS idx_pumps_tank ON pumps(tank_id);
CREATE INDEX IF NOT EXISTS idx_nozzles_pump ON nozzles(pump_id);
CREATE INDEX IF NOT EXISTS idx_caixa_item_cash_register ON caixa_item(cash_register_id);
CREATE INDEX IF NOT EXISTS idx_caixa_item_user ON caixa_item(user_id);
CREATE INDEX IF NOT EXISTS idx_comanda_user ON comanda(user_id);
CREATE INDEX IF NOT EXISTS idx_comanda_item_command ON comanda_item(command_id);
CREATE INDEX IF NOT EXISTS idx_comanda_item_product ON comanda_item(product_id);
CREATE INDEX IF NOT EXISTS idx_abastecimento_tank ON abastecimento(tank_id);
CREATE INDEX IF NOT EXISTS idx_abastecimento_pump ON abastecimento(pump_id);
