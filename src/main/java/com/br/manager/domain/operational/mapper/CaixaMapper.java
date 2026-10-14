package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.CaixaInputDTO;
import com.br.manager.domain.operational.dto.CaixaResponseDTO;
import com.br.manager.domain.operational.entity.Caixa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CaixaMapper {
    public Caixa toEntity(CaixaInputDTO dto) {
        if (dto == null) return null;
        Caixa c = new Caixa();
        c.setName(dto.getName());
        c.setSynchronize(dto.getSynchronize());
        return c;
    }

    public CaixaResponseDTO toDto(Caixa c) {
        if (c == null) return null;
        CaixaResponseDTO dto = new CaixaResponseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setSynchronize(c.getSynchronize());
        return dto;
    }

    public List<CaixaResponseDTO> toDtoList(List<Caixa> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(CaixaInputDTO dto, Caixa entity) {
        if (dto == null || entity == null) return;
        entity.setName(dto.getName());
        entity.setSynchronize(dto.getSynchronize());
    }
}
