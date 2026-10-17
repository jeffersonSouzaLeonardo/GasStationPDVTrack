package com.br.manager.domain.financial.mapper;

import com.br.manager.domain.financial.dto.CashItemInputDTO;
import com.br.manager.domain.financial.dto.CashItemResponseDTO;
import com.br.manager.domain.financial.entity.CashItem;
import com.br.manager.domain.operational.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CashItemMapper {

    private final CashMapper cashMapper;
    private final UserMapper userMapper;

    public CashItemMapper(CashMapper cashMapper, UserMapper userMapper) {
        this.cashMapper = cashMapper;
        this.userMapper = userMapper;
    }

    public CashItem toEntity(CashItemInputDTO dto) {
        if (dto == null) return null;
        CashItem ci = new CashItem();
        ci.setCash(dto.getCash());
        ci.setOpeningDateTime(dto.getOpeningDateTime());
        ci.setUser(dto.getUser());
        ci.setClosingDateTime(dto.getClosingDateTime());
        ci.setSynchronize(dto.getSynchronize());
        return ci;
    }

    public CashItemResponseDTO toDto(CashItem ci) {
        if (ci == null) return null;
        CashItemResponseDTO dto = new CashItemResponseDTO();
        dto.setId(ci.getId());
        dto.setCashRegister(cashMapper.toDto(ci.getCash()));
        dto.setOpeningDateTime(ci.getOpeningDateTime());
        dto.setUser(userMapper.toDto(ci.getUser()));
        dto.setClosingDateTime(ci.getClosingDateTime());
        dto.setSynchronize(ci.getSynchronize());
        return dto;
    }

    public List<CashItemResponseDTO> toDtoList(List<CashItem> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(CashItemInputDTO dto, CashItem entity) {
        if (dto == null || entity == null) return;
        entity.setCash(dto.getCash());
        entity.setOpeningDateTime(dto.getOpeningDateTime());
        entity.setUser(dto.getUser());
        entity.setClosingDateTime(dto.getClosingDateTime());
        entity.setSynchronize(dto.getSynchronize());
    }
}
