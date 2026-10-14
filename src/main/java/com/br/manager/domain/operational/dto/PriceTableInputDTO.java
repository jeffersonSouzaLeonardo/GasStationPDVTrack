package com.br.manager.domain.operational.dto;

public class PriceTableInputDTO {
    private String name;
    private java.util.UUID stationId;
    private Boolean active;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public java.util.UUID getStationId() { return stationId; }
    public void setStationId(java.util.UUID stationId) { this.stationId = stationId; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
