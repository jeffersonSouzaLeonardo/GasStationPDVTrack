package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.RecebimentoInputDTO;
import com.br.manager.domain.operational.dto.RecebimentoResponseDTO;
import com.br.manager.domain.operational.entity.Recebimento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecebimentoMapper {
    public Recebimento toEntity(RecebimentoInputDTO dto) {
        if (dto == null) return null;
        Recebimento r = new Recebimento();
        r.setCommandId(dto.getCommandId());
        r.setTotalValue(dto.getTotalValue());
        r.setDiscountValue(dto.getDiscountValue());
        r.setPaymentMethodId(dto.getPaymentMethodId());
        r.setDataTime(dto.getDataTime());
        r.setSynchronize(dto.getSynchronize());
        return r;
    }

    public RecebimentoResponseDTO toDto(Recebimento r) {
        if (r == null) return null;
        RecebimentoResponseDTO dto = new RecebimentoResponseDTO();
        dto.setId(r.getId());
        dto.setCommandId(r.getCommandId());
        dto.setTotalValue(r.getTotalValue());
        dto.setDiscountValue(r.getDiscountValue());
        dto.setPaymentMethodId(r.getPaymentMethodId());
        dto.setDataTime(r.getDataTime());
        dto.setSynchronize(r.getSynchronize());
        return dto;
    }

    public List<RecebimentoResponseDTO> toDtoList(List<Recebimento> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(RecebimentoInputDTO dto, Recebimento entity) {
        if (dto == null || entity == null) return;
        entity.setCommandId(dto.getCommandId());
        entity.setTotalValue(dto.getTotalValue());
        entity.setDiscountValue(dto.getDiscountValue());
        entity.setPaymentMethodId(dto.getPaymentMethodId());
        entity.setDataTime(dto.getDataTime());
        entity.setSynchronize(dto.getSynchronize());
    }
}
