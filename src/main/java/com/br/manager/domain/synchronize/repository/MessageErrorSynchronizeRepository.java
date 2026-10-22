package com.br.manager.domain.synchronize.repository;

import com.br.manager.domain.synchronize.entity.MessageErrorSynchronize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageErrorSynchronizeRepository extends JpaRepository<MessageErrorSynchronize, UUID> {

    List<MessageErrorSynchronize> findByDocumentSynchronizeId(UUID documentSynchronizeId);
}
