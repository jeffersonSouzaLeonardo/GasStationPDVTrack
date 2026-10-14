package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.CaixaItemInputDTO;
import com.br.manager.domain.operational.dto.CaixaItemResponseDTO;
import com.br.manager.domain.operational.entity.CaixaItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CaixaItemMapper {
    public CaixaItem toEntity(CaixaItemInputDTO dto) {
        if (dto == null) return null;
        CaixaItem ci = new CaixaItem();
        ci.setCashRegisterId(dto.getCashRegisterId());
        ci.setOpeningDateTime(dto.getOpeningDateTime());
        ci.setUserId(dto.getUserId());
        ci.setClosingDateTime(dto.getClosingDateTime());
        ci.setSynchronize(dto.getSynchronize());
        return ci;
    }

    public CaixaItemResponseDTO toDto(CaixaItem ci) {
        if (ci == null) return null;
        CaixaItemResponseDTO dto = new CaixaItemResponseDTO();
        dto.setId(ci.getId());
        dto.setCashRegisterId(ci.getCashRegisterId());
        dto.setOpeningDateTime(ci.getOpeningDateTime());
        dto.setUserId(ci.getUserId());
        dto.setClosingDateTime(ci.getClosingDateTime());
        dto.setSynchronize(ci.getSynchronize());
        return dto;
    }

    public List<CaixaItemResponseDTO> toDtoList(List<CaixaItem> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(CaixaItemInputDTO dto, CaixaItem entity) {
        if (dto == null || entity == null) return;
        entity.setCashRegisterId(dto.getCashRegisterId());
        entity.setOpeningDateTime(dto.getOpeningDateTime());
        entity.setUserId(dto.getUserId());
        entity.setClosingDateTime(dto.getClosingDateTime());
        entity.setSynchronize(dto.getSynchronize());
    }
}
