package com.br.manager.domain.financial.mapper;

import com.br.manager.domain.financial.dto.ReceiptInputDTO;
import com.br.manager.domain.financial.dto.ReceiptResponseDTO;
import com.br.manager.domain.financial.entity.Receipt;
import com.br.manager.domain.sale.mapper.CommandMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptMapper {

    private final CommandMapper commandMapper;
    private final PaymentMethodMapper paymentMethodMapper;

    public ReceiptMapper(CommandMapper commandMapper, PaymentMethodMapper paymentMethodMapper) {
        this.commandMapper = commandMapper;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    public Receipt toEntity(ReceiptInputDTO dto) {
        if (dto == null) return null;
        Receipt r = new Receipt();
        r.setCommand(dto.getCommand());
        r.setTotalValue(dto.getTotalValue());
        r.setDiscountValue(dto.getDiscountValue());
        r.setPaymentMethod(dto.getPaymentMethod());
        r.setDataTime(dto.getDataTime());
        r.setSynchronize(dto.getSynchronize());
        return r;
    }

    public ReceiptResponseDTO toDto(Receipt r) {
        if (r == null) return null;
        ReceiptResponseDTO dto = new ReceiptResponseDTO();
        dto.setId(r.getId());
        dto.setCommand(commandMapper.toDto(r.getCommand()));
        dto.setTotalValue(r.getTotalValue());
        dto.setDiscountValue(r.getDiscountValue());
        dto.setPaymentMethod(paymentMethodMapper.toDto(r.getPaymentMethod()));
        dto.setDataTime(r.getDataTime());
        dto.setSynchronize(r.getSynchronize());
        return dto;
    }

    public List<ReceiptResponseDTO> toDtoList(List<Receipt> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(ReceiptInputDTO dto, Receipt entity) {
        if (dto == null || entity == null) return;
        entity.setCommand(dto.getCommand());
        entity.setTotalValue(dto.getTotalValue());
        entity.setDiscountValue(dto.getDiscountValue());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setDataTime(dto.getDataTime());
        entity.setSynchronize(dto.getSynchronize());
    }
}
