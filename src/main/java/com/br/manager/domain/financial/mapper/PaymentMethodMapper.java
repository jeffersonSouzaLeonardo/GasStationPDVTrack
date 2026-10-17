package com.br.manager.domain.financial.mapper;

import com.br.manager.domain.financial.dto.PaymentMethodInputDTO;
import com.br.manager.domain.financial.dto.PaymentMethodResponseDTO;
import com.br.manager.domain.financial.entity.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodMapper {
    public PaymentMethod toEntity(PaymentMethodInputDTO dto) {
        if (dto == null) return null;
        PaymentMethod fp = new PaymentMethod();
        fp.setName(dto.getName());
        return fp;
    }

    public PaymentMethodResponseDTO toDto(PaymentMethod fp) {
        if (fp == null) return null;
        PaymentMethodResponseDTO dto = new PaymentMethodResponseDTO();
        dto.setId(fp.getId());
        dto.setName(fp.getName());
        return dto;
    }

    public List<PaymentMethodResponseDTO> toDtoList(List<PaymentMethod> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(PaymentMethodInputDTO dto, PaymentMethod entity) {
        if (dto == null || entity == null) return;
        entity.setName(dto.getName());
    }
}
