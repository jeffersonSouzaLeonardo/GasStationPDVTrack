package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.ComandaInputDTO;
import com.br.manager.domain.operational.dto.ComandaResponseDTO;
import com.br.manager.domain.operational.entity.Comanda;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComandaMapper {
    public Comanda toEntity(ComandaInputDTO dto) {
        if (dto == null) return null;
        Comanda c = new Comanda();
        c.setDataTime(dto.getDataTime());
        c.setUserId(dto.getUserId());
        c.setStatus(dto.getStatus());
        return c;
    }

    public ComandaResponseDTO toDto(Comanda c) {
        if (c == null) return null;
        ComandaResponseDTO dto = new ComandaResponseDTO();
        dto.setId(c.getId());
        dto.setDataTime(c.getDataTime());
        dto.setUserId(c.getUserId());
        dto.setStatus(c.getStatus());
        return dto;
    }

    public List<ComandaResponseDTO> toDtoList(List<Comanda> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(ComandaInputDTO dto, Comanda entity) {
        if (dto == null || entity == null) return;
        entity.setDataTime(dto.getDataTime());
        entity.setUserId(dto.getUserId());
        entity.setStatus(dto.getStatus());
    }
}
