package com.br.manager.domain.price.mapper;

import com.br.manager.domain.operational.mapper.ProductMapper;
import com.br.manager.domain.price.dto.PriceItemInputDTO;
import com.br.manager.domain.price.dto.PriceItemResponseDTO;
import com.br.manager.domain.price.entity.PriceItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceItemMapper {

    private final PriceTableMapper priceTableMapper;
    private final ProductMapper productMapper;

    public PriceItemMapper(PriceTableMapper priceTableMapper, ProductMapper productMapper) {
        this.priceTableMapper = priceTableMapper;
        this.productMapper = productMapper;
    }

    public PriceItem toEntity(PriceItemInputDTO dto) {
        if (dto == null) return null;
        PriceItem p = new PriceItem();
        p.setPriceTable(dto.getPriceTable());
        p.setProduct(dto.getProduct());
        p.setUnitPrice(dto.getUnitPrice());
        return p;
    }

    public PriceItemResponseDTO toDto(PriceItem p) {
        if (p == null) return null;
        PriceItemResponseDTO dto = new PriceItemResponseDTO();
        dto.setId(p.getId());
        dto.setPriceTable(priceTableMapper.toDto(p.getPriceTable()));
        dto.setProduct(productMapper.toDto(p.getProduct()));
        dto.setUnitPrice(p.getUnitPrice());
        return dto;
    }

    public List<PriceItemResponseDTO> toDtoList(List<PriceItem> items) {
        if (items == null) return null;
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(PriceItemInputDTO dto, PriceItem entity) {
        if (dto == null || entity == null) return;
        entity.setPriceTable(dto.getPriceTable());
        entity.setProduct(dto.getProduct());
        entity.setUnitPrice(dto.getUnitPrice());
    }
}
