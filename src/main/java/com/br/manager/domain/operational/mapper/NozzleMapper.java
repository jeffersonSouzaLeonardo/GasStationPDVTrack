package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.NozzleInputDTO;
import com.br.manager.domain.operational.dto.NozzleResponseDTO;
import com.br.manager.domain.operational.entity.Nozzle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NozzleMapper {

    private final PumpMapper pumpMapper;
    private final TankMapper tankMapper;
    private final ProductMapper productMapper;

    public NozzleMapper(PumpMapper pumpMapper, TankMapper tankMapper, ProductMapper productMapper) {
        this.pumpMapper = pumpMapper;
        this.tankMapper = tankMapper;
        this.productMapper = productMapper;
    }

    public Nozzle toEntity(NozzleInputDTO dto) {
        if (dto == null) return null;
        Nozzle n = new Nozzle();
        n.setPump(dto.getPump());
        n.setTank(dto.getTank());
        n.setCode(dto.getCode());
        n.setProduct(dto.getProduct());
        n.setMeterNumber(dto.getMeterNumber());
        n.setActive(dto.getActive() != null ? dto.getActive() : Boolean.TRUE);
        return n;
    }

    public NozzleResponseDTO toDto(Nozzle n) {
        if (n == null) return null;
        NozzleResponseDTO dto = new NozzleResponseDTO();
        dto.setId(n.getId());
        dto.setPump(pumpMapper.toDto(n.getPump()));
        dto.setTank(tankMapper.toDto(n.getTank()));
        dto.setCode(n.getCode());
        dto.setProduct(productMapper.toDto(n.getProduct()));
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
        entity.setPump(dto.getPump());
        entity.setTank(dto.getTank());
        entity.setCode(dto.getCode());
        entity.setProduct(dto.getProduct());
        entity.setMeterNumber(dto.getMeterNumber());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
    }
}
