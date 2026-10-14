package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.PumpInputDTO;
import com.br.manager.domain.operational.dto.PumpResponseDTO;
import com.br.manager.domain.operational.entity.Pump;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PumpMapper {
    public Pump toEntity(PumpInputDTO dto) {
        if (dto == null) return null;
        Pump p = new Pump();
        p.setCode(dto.getCode());
        p.setLocation(dto.getLocation());
        p.setActive(dto.getActive() != null ? dto.getActive() : Boolean.TRUE);
        return p;
    }

    public PumpResponseDTO toDto(Pump p) {
        if (p == null) return null;
        PumpResponseDTO dto = new PumpResponseDTO();
        dto.setId(p.getId());
        dto.setCode(p.getCode());
        dto.setLocation(p.getLocation());
        dto.setActive(p.getActive());
        return dto;
    }

    public List<PumpResponseDTO> toDtoList(List<Pump> pumps) {
        if (pumps == null) return null;
        return pumps.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(PumpInputDTO dto, Pump entity) {
        if (dto == null || entity == null) return;
        entity.setCode(dto.getCode());
        entity.setLocation(dto.getLocation());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
    }
}
