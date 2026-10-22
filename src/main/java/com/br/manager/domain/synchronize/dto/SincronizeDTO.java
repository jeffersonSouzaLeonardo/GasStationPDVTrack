package com.br.manager.domain.synchronize.dto;

import java.time.LocalDateTime;

public class SincronizeDTO {
    private String nameTable;
    private LocalDateTime lastSincronize;

    public SincronizeDTO() {
    }

    public SincronizeDTO(String nameTable, LocalDateTime lastSincronize) {
        this.nameTable = nameTable;
        this.lastSincronize = lastSincronize;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public LocalDateTime getLastSincronize() {
        return lastSincronize;
    }

    public void setLastSincronize(LocalDateTime lastSincronize) {
        this.lastSincronize = lastSincronize;
    }
}
