package com.br.manager.domain.sale.mapper;

import com.br.manager.domain.operational.mapper.UserMapper;
import com.br.manager.domain.sale.dto.CommandInputDTO;
import com.br.manager.domain.sale.dto.CommandResponseDTO;
import com.br.manager.domain.sale.entity.Command;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandMapper {

    private final UserMapper userMapper;

    public CommandMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Command toEntity(CommandInputDTO dto) {
        if (dto == null) return null;
        Command c = new Command();
        c.setDataTime(dto.getDataTime());
        c.setUser(dto.getUser());
        c.setStatus(dto.getStatus());
        return c;
    }

    public CommandResponseDTO toDto(Command c) {
        if (c == null) return null;
        CommandResponseDTO dto = new CommandResponseDTO();
        dto.setId(c.getId());
        dto.setDataTime(c.getDataTime());
        dto.setUser(userMapper.toDto(c.getUser()));
        dto.setStatus(c.getStatus());
        return dto;
    }

    public List<CommandResponseDTO> toDtoList(List<Command> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateFromDto(CommandInputDTO dto, Command entity) {
        if (dto == null || entity == null) return;
        entity.setDataTime(dto.getDataTime());
        entity.setUser(dto.getUser());
        entity.setStatus(dto.getStatus());
    }
}
