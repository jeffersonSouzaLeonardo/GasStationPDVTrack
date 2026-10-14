package com.br.manager.domain.operational.dto;

public class PumpInputDTO {
    private String code;
    private String location;
    private Boolean active;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
