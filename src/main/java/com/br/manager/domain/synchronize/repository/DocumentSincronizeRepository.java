package com.br.manager.domain.synchronize.repository;

import com.br.manager.domain.synchronize.entity.DocumentSynchronize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentSincronizeRepository extends JpaRepository<DocumentSynchronize, UUID> {
    List<DocumentSynchronize> findBySynchronizeFalse();
}
