package com.br.manager.domain.shift.entity;

public enum CashSessionStatusEnum {
    OPEN("Aberto"),
    CLOSED("Fechado");

    private final String label;

    CashSessionStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
