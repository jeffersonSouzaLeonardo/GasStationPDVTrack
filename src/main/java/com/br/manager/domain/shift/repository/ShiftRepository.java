package com.br.manager.domain.shift.repository;

import com.br.manager.domain.shift.entity.Shift;
import com.br.manager.domain.shift.entity.ShiftStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, UUID> {

    Optional<Shift> findFirstByStatusOrderByOpenedAtDesc(ShiftStatusEnum status);

    List<Shift> findAllByOrderByOpenedAtDesc();
}
