package com.br.manager.domain.financial.mapper;

import com.br.manager.domain.financial.dto.CashInputDTO;
import com.br.manager.domain.financial.dto.CashResponseDTO;
import com.br.manager.domain.financial.entity.Cash;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CashMapper {
    public Cash toEntity(CashInputDTO dto) {
        if (dto == null) return null;
        Cash c = new Cash();
        c.setName(dto.getName());
        c.setSynchronize(dto.getSynchronize());
        return c;
    }

    public CashResponseDTO toDto(Cash c) {
        if (c == null) return null;
        CashResponseDTO dto = new CashResponseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setSynchronize(c.getSynchronize());
        return dto;
    }

    public List<CashResponseDTO> toDtoList(List<Cash> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(CashInputDTO dto, Cash entity) {
        if (dto == null || entity == null) return;
        entity.setName(dto.getName());
        entity.setSynchronize(dto.getSynchronize());
    }
}
