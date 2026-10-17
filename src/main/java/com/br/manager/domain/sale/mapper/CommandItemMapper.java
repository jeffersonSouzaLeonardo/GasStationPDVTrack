package com.br.manager.domain.sale.mapper;

import com.br.manager.domain.operational.mapper.ProductMapper;
import com.br.manager.domain.price.mapper.PriceTableMapper;
import com.br.manager.domain.sale.dto.CommandItemInputDTO;
import com.br.manager.domain.sale.dto.CommandItemResponseDTO;
import com.br.manager.domain.sale.entity.CommandItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandItemMapper {

    private final CommandMapper commandMapper;
    private final ProductMapper productMapper;
    private final PriceTableMapper priceTableMapper;

    public CommandItemMapper(CommandMapper commandMapper, ProductMapper productMapper, PriceTableMapper priceTableMapper) {
        this.commandMapper = commandMapper;
        this.productMapper = productMapper;
        this.priceTableMapper = priceTableMapper;
    }

    public CommandItem toEntity(CommandItemInputDTO dto) {
        if (dto == null) return null;
        CommandItem ci = new CommandItem();
        ci.setCommand(dto.getCommand());
        ci.setProduct(dto.getProduct());
        ci.setUnitValue(dto.getUnitValue());
        ci.setDiscount(dto.getDiscount());
        ci.setPriceTable(dto.getPriceTable());
        return ci;
    }

    public CommandItemResponseDTO toDto(CommandItem ci) {
        if (ci == null) return null;
        CommandItemResponseDTO dto = new CommandItemResponseDTO();
        dto.setId(ci.getId());
        dto.setCommand(commandMapper.toDto(ci.getCommand()));
        dto.setProduct(productMapper.toDto(ci.getProduct()));
        dto.setUnitValue(ci.getUnitValue());
        dto.setDiscount(ci.getDiscount());
        dto.setPriceTable(priceTableMapper.toDto(ci.getPriceTable()));
        return dto;
    }

    public List<CommandItemResponseDTO> toDtoList(List<CommandItem> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(CommandItemInputDTO dto, CommandItem entity) {
        if (dto == null || entity == null) return;
        entity.setCommand(dto.getCommand());
        entity.setProduct(dto.getProduct());
        entity.setUnitValue(dto.getUnitValue());
        entity.setDiscount(dto.getDiscount());
        entity.setPriceTable(dto.getPriceTable());
    }
}
