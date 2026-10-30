package com.br.manager.domain.shift.repository;

import com.br.manager.domain.shift.entity.CashSession;
import com.br.manager.domain.shift.entity.CashSessionStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CashSessionRepository extends JpaRepository<CashSession, UUID> {

    @Query("select cs from CashSession cs "
            + "join fetch cs.shift "
            + "join fetch cs.openedBy "
            + "left join fetch cs.closedBy "
            + "order by cs.openedAt desc")
    List<CashSession> findAllWithDetails();

    @Query("select cs from CashSession cs "
            + "join fetch cs.shift "
            + "join fetch cs.openedBy "
            + "left join fetch cs.closedBy "
            + "where cs.openedBy.id = :userId and cs.status = :status")
    Optional<CashSession> findByOpenedByIdAndStatus(@Param("userId") UUID userId,
                                                    @Param("status") CashSessionStatusEnum status);
}
