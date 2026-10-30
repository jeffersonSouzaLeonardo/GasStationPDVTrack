package com.br.manager.domain.shift.entity;

public enum ShiftStatusEnum {
    OPEN("Aberto"),
    CLOSED("Fechado");

    private final String label;

    ShiftStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
