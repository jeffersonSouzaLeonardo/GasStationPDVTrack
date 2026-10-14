package com.br.manager.model;

public enum ConnectionStatus {
    DISCONNECTED("Desconectado"),
    CONNECTING("Conectando..."),
    CONNECTED("Conectado");

    private final String label;

    ConnectionStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
