package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.TankInputDTO;
import com.br.manager.domain.operational.dto.TankResponseDTO;
import com.br.manager.domain.operational.entity.Tank;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TankMapper {

    private final ProductMapper productMapper;

    public TankMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Tank toEntity(TankInputDTO dto) {
        if (dto == null) return null;
        Tank t = new Tank();
        t.setCode(dto.getCode());
        t.setProduct(dto.getProduct());
        t.setCapacityLiters(dto.getCapacityLiters());
        t.setDeadStockLiters(dto.getDeadStockLiters());
        t.setCurrentBookLiters(dto.getCurrentBookLiters());
        t.setActive(dto.getActive() != null ? dto.getActive() : Boolean.TRUE);
        return t;
    }

    public TankResponseDTO toDto(Tank t) {
        if (t == null) return null;
        TankResponseDTO dto = new TankResponseDTO();
        dto.setId(t.getId());
        dto.setCode(t.getCode());
        dto.setProduct(productMapper.toDto(t.getProduct()));
        dto.setCapacityLiters(t.getCapacityLiters());
        dto.setDeadStockLiters(t.getDeadStockLiters());
        dto.setCurrentBookLiters(t.getCurrentBookLiters());
        dto.setActive(t.getActive());
        return dto;
    }

    public List<TankResponseDTO> toDtoList(List<Tank> tanks) {
        if (tanks == null) return null;
        return tanks.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(TankInputDTO dto, Tank entity) {
        if (dto == null || entity == null) return;
        entity.setCode(dto.getCode());
        entity.setProduct(dto.getProduct());
        entity.setCapacityLiters(dto.getCapacityLiters());
        entity.setDeadStockLiters(dto.getDeadStockLiters());
        entity.setCurrentBookLiters(dto.getCurrentBookLiters());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
    }
}
