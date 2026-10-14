package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.NozzleInputDTO;
import com.br.manager.domain.operational.dto.NozzleResponseDTO;
import com.br.manager.domain.operational.entity.Nozzle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NozzleMapper {
    public Nozzle toEntity(NozzleInputDTO dto) {
        if (dto == null) return null;
        Nozzle n = new Nozzle();
        n.setPumpId(dto.getPumpId());
        n.setTankId(dto.getTankId());
        n.setCode(dto.getCode());
        n.setProductId(dto.getProductId());
        n.setMeterNumber(dto.getMeterNumber());
        n.setActive(dto.getActive() != null ? dto.getActive() : Boolean.TRUE);
        return n;
    }

    public NozzleResponseDTO toDto(Nozzle n) {
        if (n == null) return null;
        NozzleResponseDTO dto = new NozzleResponseDTO();
        dto.setId(n.getId());
        dto.setPumpId(n.getPumpId());
        dto.setTankId(n.getTankId());
        dto.setCode(n.getCode());
        dto.setProductId(n.getProductId());
        dto.setMeterNumber(n.getMeterNumber());
        dto.setActive(n.getActive());
        return dto;
    }

    public List<NozzleResponseDTO> toDtoList(List<Nozzle> nozzles) {
        if (nozzles == null) return null;
        return nozzles.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(NozzleInputDTO dto, Nozzle entity) {
        if (dto == null || entity == null) return;
        entity.setPumpId(dto.getPumpId());
        entity.setTankId(dto.getTankId());
        entity.setCode(dto.getCode());
        entity.setProductId(dto.getProductId());
        entity.setMeterNumber(dto.getMeterNumber());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
    }
}
