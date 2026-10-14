package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.ComandaItemInputDTO;
import com.br.manager.domain.operational.dto.ComandaItemResponseDTO;
import com.br.manager.domain.operational.entity.ComandaItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComandaItemMapper {
    public ComandaItem toEntity(ComandaItemInputDTO dto) {
        if (dto == null) return null;
        ComandaItem ci = new ComandaItem();
        ci.setCommandId(dto.getCommandId());
        ci.setProductId(dto.getProductId());
        ci.setUnitValue(dto.getUnitValue());
        ci.setDiscount(dto.getDiscount());
        ci.setPriceTableId(dto.getPriceTableId());
        return ci;
    }

    public ComandaItemResponseDTO toDto(ComandaItem ci) {
        if (ci == null) return null;
        ComandaItemResponseDTO dto = new ComandaItemResponseDTO();
        dto.setId(ci.getId());
        dto.setCommandId(ci.getCommandId());
        dto.setProductId(ci.getProductId());
        dto.setUnitValue(ci.getUnitValue());
        dto.setDiscount(ci.getDiscount());
        dto.setPriceTableId(ci.getPriceTableId());
        return dto;
    }

    public List<ComandaItemResponseDTO> toDtoList(List<ComandaItem> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(ComandaItemInputDTO dto, ComandaItem entity) {
        if (dto == null || entity == null) return;
        entity.setCommandId(dto.getCommandId());
        entity.setProductId(dto.getProductId());
        entity.setUnitValue(dto.getUnitValue());
        entity.setDiscount(dto.getDiscount());
        entity.setPriceTableId(dto.getPriceTableId());
    }
}
