package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.PriceTableInputDTO;
import com.br.manager.domain.operational.dto.PriceTableResponseDTO;
import com.br.manager.domain.operational.entity.PriceTable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceTableMapper {
    public PriceTable toEntity(PriceTableInputDTO dto) {
        if (dto == null) return null;
        PriceTable p = new PriceTable();
        p.setName(dto.getName());
        p.setStationId(dto.getStationId());
        p.setActive(dto.getActive() != null ? dto.getActive() : Boolean.TRUE);
        return p;
    }

    public PriceTableResponseDTO toDto(PriceTable p) {
        if (p == null) return null;
        PriceTableResponseDTO dto = new PriceTableResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setStationId(p.getStationId());
        dto.setActive(p.getActive());
        dto.setCreatedAt(p.getCreatedAt());
        return dto;
    }

    public List<PriceTableResponseDTO> toDtoList(List<PriceTable> priceTables) {
        if (priceTables == null) return null;
        return priceTables.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(PriceTableInputDTO dto, PriceTable entity) {
        if (dto == null || entity == null) return;
        entity.setName(dto.getName());
        entity.setStationId(dto.getStationId());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
    }
}
