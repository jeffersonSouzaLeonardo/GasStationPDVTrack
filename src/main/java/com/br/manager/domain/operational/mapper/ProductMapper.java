package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.ProductInputDTO;
import com.br.manager.domain.operational.dto.ProductResponseDTO;
import com.br.manager.domain.operational.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product toEntity(ProductInputDTO dto) {
        if (dto == null) return null;
        Product p = new Product();
        p.setSku(dto.getSku());
        p.setName(dto.getName());
        p.setProductType(dto.getProductType());
        p.setUnit(dto.getUnit());
        p.setAnpCode(dto.getAnpCode());
        p.setNcm(dto.getNcm());
        p.setCest(dto.getCest());
        p.setActive(dto.getActive() != null ? dto.getActive() : Boolean.TRUE);
        return p;
    }

    public ProductResponseDTO toDto(Product p) {
        if (p == null) return null;
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(p.getId());
        dto.setSku(p.getSku());
        dto.setName(p.getName());
        dto.setProductType(p.getProductType());
        dto.setUnit(p.getUnit());
        dto.setAnpCode(p.getAnpCode());
        dto.setNcm(p.getNcm());
        dto.setCest(p.getCest());
        dto.setActive(p.getActive());
        dto.setCreatedAt(p.getCreatedAt());
        return dto;
    }

    public List<ProductResponseDTO> toDtoList(List<Product> products) {
        if (products == null) return null;
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(ProductInputDTO dto, Product entity) {
        if (dto == null || entity == null) return;
        entity.setSku(dto.getSku());
        entity.setName(dto.getName());
        entity.setProductType(dto.getProductType());
        entity.setUnit(dto.getUnit());
        entity.setAnpCode(dto.getAnpCode());
        entity.setNcm(dto.getNcm());
        entity.setCest(dto.getCest());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
    }
}
