package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.FormaPagamentoInputDTO;
import com.br.manager.domain.operational.dto.FormaPagamentoResponseDTO;
import com.br.manager.domain.operational.entity.FormaPagamento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoMapper {
    public FormaPagamento toEntity(FormaPagamentoInputDTO dto) {
        if (dto == null) return null;
        FormaPagamento fp = new FormaPagamento();
        fp.setName(dto.getName());
        return fp;
    }

    public FormaPagamentoResponseDTO toDto(FormaPagamento fp) {
        if (fp == null) return null;
        FormaPagamentoResponseDTO dto = new FormaPagamentoResponseDTO();
        dto.setId(fp.getId());
        dto.setName(fp.getName());
        return dto;
    }

    public List<FormaPagamentoResponseDTO> toDtoList(List<FormaPagamento> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(FormaPagamentoInputDTO dto, FormaPagamento entity) {
        if (dto == null || entity == null) return;
        entity.setName(dto.getName());
    }
}
