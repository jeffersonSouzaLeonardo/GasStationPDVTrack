package com.br.manager.domain.shift.service;

import com.br.manager.domain.shift.entity.Shift;
import com.br.manager.domain.shift.entity.ShiftStatusEnum;
import com.br.manager.domain.shift.repository.ShiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Transactional(readOnly = true)
    public List<Shift> findAll() {
        return shiftRepository.findAllByOrderByOpenedAtDesc();
    }

    @Transactional(readOnly = true)
    public Optional<Shift> getActiveShift() {
        return shiftRepository.findFirstByStatusOrderByOpenedAtDesc(ShiftStatusEnum.OPEN);
    }

    @Transactional
    public Shift openShift(String name) {
        Shift shift = new Shift();
        shift.setId(UUID.randomUUID());
        shift.setName(name);
        shift.setStatus(ShiftStatusEnum.OPEN);
        shift.setOpenedAt(LocalDateTime.now());
        return shiftRepository.save(shift);
    }

    @Transactional
    public Shift closeShift(UUID shiftId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new IllegalArgumentException("Turno não encontrado."));
        shift.setStatus(ShiftStatusEnum.CLOSED);
        shift.setClosedAt(LocalDateTime.now());
        return shiftRepository.save(shift);
    }
}
