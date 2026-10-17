package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.SupplierInputDTO;
import com.br.manager.domain.operational.dto.SupplierResponseDTO;
import com.br.manager.domain.operational.entity.Supply;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {

    private final TankMapper tankMapper;
    private final PumpMapper pumpMapper;

    public SupplierMapper(TankMapper tankMapper, PumpMapper pumpMapper) {
        this.tankMapper = tankMapper;
        this.pumpMapper = pumpMapper;
    }

    public Supply toEntity(SupplierInputDTO dto) {
        if (dto == null) return null;
        Supply a = new Supply();
        a.setDataTime(dto.getDataTime());
        a.setTank(dto.getTank());
        a.setPump(dto.getPump());
        a.setVolume(dto.getVolume());
        a.setTotalValue(dto.getTotalValue());
        return a;
    }

    public SupplierResponseDTO toDto(Supply a) {
        if (a == null) return null;
        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(a.getId());
        dto.setDataTime(a.getDataTime());
        dto.setTank(tankMapper.toDto(a.getTank()));
        dto.setPump(pumpMapper.toDto(a.getPump()));
        dto.setVolume(a.getVolume());
        dto.setTotalValue(a.getTotalValue());
        return dto;
    }

    public List<SupplierResponseDTO> toDtoList(List<Supply> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(SupplierInputDTO dto, Supply entity) {
        if (dto == null || entity == null) return;
        entity.setDataTime(dto.getDataTime());
        entity.setTank(dto.getTank());
        entity.setPump(dto.getPump());
        entity.setVolume(dto.getVolume());
        entity.setTotalValue(dto.getTotalValue());
    }
}
