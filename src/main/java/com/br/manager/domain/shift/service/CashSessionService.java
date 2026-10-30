package com.br.manager.domain.shift.service;

import com.br.manager.domain.operational.entity.User;
import com.br.manager.domain.shift.entity.CashSession;
import com.br.manager.domain.shift.entity.CashSessionStatusEnum;
import com.br.manager.domain.shift.entity.Shift;
import com.br.manager.domain.shift.repository.CashSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CashSessionService {

    private final CashSessionRepository cashSessionRepository;

    public CashSessionService(CashSessionRepository cashSessionRepository) {
        this.cashSessionRepository = cashSessionRepository;
    }

    @Transactional(readOnly = true)
    public List<CashSession> findAll() {
        return cashSessionRepository.findAllWithDetails();
    }

    @Transactional(readOnly = true)
    public Optional<CashSession> getOpenSessionForUser(UUID userId) {
        return cashSessionRepository.findByOpenedByIdAndStatus(userId, CashSessionStatusEnum.OPEN);
    }

    @Transactional
    public CashSession openSession(Shift shift, User operator, BigDecimal openingAmount) {
        CashSession session = new CashSession();
        session.setId(UUID.randomUUID());
        session.setShift(shift);
        session.setOpenedBy(operator);
        session.setOpenedAt(LocalDateTime.now());
        session.setOpeningAmount(openingAmount == null ? BigDecimal.ZERO : openingAmount);
        session.setStatus(CashSessionStatusEnum.OPEN);
        session.setSynchronize(false);
        return cashSessionRepository.save(session);
    }

    @Transactional
    public CashSession closeSession(UUID sessionId, User closedBy, BigDecimal closingAmount) {
        CashSession session = cashSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão de caixa não encontrada."));
        session.setStatus(CashSessionStatusEnum.CLOSED);
        session.setClosedBy(closedBy);
        session.setClosedAt(LocalDateTime.now());
        session.setClosingAmount(closingAmount);
        return cashSessionRepository.save(session);
    }
}
