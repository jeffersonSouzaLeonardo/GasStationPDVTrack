package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.PriceItemInputDTO;
import com.br.manager.domain.operational.dto.PriceItemResponseDTO;
import com.br.manager.domain.operational.entity.PriceItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceItemMapper {
    public PriceItem toEntity(PriceItemInputDTO dto) {
        if (dto == null) return null;
        PriceItem p = new PriceItem();
        p.setPriceTableId(dto.getPriceTableId());
        p.setProductId(dto.getProductId());
        p.setUnitPrice(dto.getUnitPrice());
        return p;
    }

    public PriceItemResponseDTO toDto(PriceItem p) {
        if (p == null) return null;
        PriceItemResponseDTO dto = new PriceItemResponseDTO();
        dto.setId(p.getId());
        dto.setPriceTableId(p.getPriceTableId());
        dto.setProductId(p.getProductId());
        dto.setUnitPrice(p.getUnitPrice());
        return dto;
    }

    public List<PriceItemResponseDTO> toDtoList(List<PriceItem> items) {
        if (items == null) return null;
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(PriceItemInputDTO dto, PriceItem entity) {
        if (dto == null || entity == null) return;
        entity.setPriceTableId(dto.getPriceTableId());
        entity.setProductId(dto.getProductId());
        entity.setUnitPrice(dto.getUnitPrice());
    }
}
