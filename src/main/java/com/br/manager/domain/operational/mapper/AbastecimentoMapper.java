package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.AbastecimentoInputDTO;
import com.br.manager.domain.operational.dto.AbastecimentoResponseDTO;
import com.br.manager.domain.operational.entity.Abastecimento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AbastecimentoMapper {
    public Abastecimento toEntity(AbastecimentoInputDTO dto) {
        if (dto == null) return null;
        Abastecimento a = new Abastecimento();
        a.setDataTime(dto.getDataTime());
        a.setTankId(dto.getTankId());
        a.setPumpId(dto.getPumpId());
        a.setVolume(dto.getVolume());
        a.setTotalValue(dto.getTotalValue());
        return a;
    }

    public AbastecimentoResponseDTO toDto(Abastecimento a) {
        if (a == null) return null;
        AbastecimentoResponseDTO dto = new AbastecimentoResponseDTO();
        dto.setId(a.getId());
        dto.setDataTime(a.getDataTime());
        dto.setTankId(a.getTankId());
        dto.setPumpId(a.getPumpId());
        dto.setVolume(a.getVolume());
        dto.setTotalValue(a.getTotalValue());
        return dto;
    }

    public List<AbastecimentoResponseDTO> toDtoList(List<Abastecimento> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(AbastecimentoInputDTO dto, Abastecimento entity) {
        if (dto == null || entity == null) return;
        entity.setDataTime(dto.getDataTime());
        entity.setTankId(dto.getTankId());
        entity.setPumpId(dto.getPumpId());
        entity.setVolume(dto.getVolume());
        entity.setTotalValue(dto.getTotalValue());
    }
}
