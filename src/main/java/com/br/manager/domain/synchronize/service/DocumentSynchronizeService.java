package com.br.manager.domain.synchronize.service;

import com.br.manager.api.SynchronizeApi;
import com.br.manager.domain.synchronize.dto.SincronizeDTO;
import com.br.manager.domain.synchronize.entity.DocumentSynchronize;
import com.br.manager.domain.synchronize.entity.MessageErrorSynchronize;
import com.br.manager.domain.synchronize.entity.SynchronizeTypeENUM;
import com.br.manager.domain.synchronize.repository.DocumentSincronizeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DocumentSynchronizeService {

    private final DocumentSincronizeRepository documentSincronizeRepository;
    private final MessageErrorSynchronizeService messageErrorSynchronizeService;
    private final SynchronizeApi synchronizeApi;
    private DocumentSynchronize documentSynchronize;

    public DocumentSynchronizeService(DocumentSincronizeRepository documentSincronizeRepository, MessageErrorSynchronizeService messageErrorSynchronizeService, SynchronizeApi synchronizeApi) {
        this.documentSincronizeRepository = documentSincronizeRepository;
        this.messageErrorSynchronizeService = messageErrorSynchronizeService;
        this.synchronizeApi = synchronizeApi;
    }

    public void save(String nameTable, String payload, SynchronizeTypeENUM synchronizeTypeENUM) {
        try {
            documentSynchronize = new DocumentSynchronize();
            documentSynchronize.setNameTable(nameTable);
            documentSynchronize.setPayload(payload);
            documentSynchronize.setSincronizeTypeENUM(synchronizeTypeENUM);
            documentSincronizeRepository.saveAndFlush(documentSynchronize);
            synchronizeApi.update(new SincronizeDTO(nameTable, java.time.LocalDateTime.now()));
        } catch (Exception e) {
            MessageErrorSynchronize messageError = messageErrorSynchronizeService.buildMessageErrorSynchronize(e.getMessage(), documentSynchronize);
            messageErrorSynchronizeService.save(messageError);
        }
    }

    public List<DocumentSynchronize> findBySynchronizeFalse() {
        return documentSincronizeRepository.findBySynchronizeFalse();
    }

    public DocumentSynchronize update(UUID id, DocumentSynchronize documentsynchronize) {
        return documentSincronizeRepository.findById(id)
                .map(existing -> {
                    existing.setNameTable(documentsynchronize.getNameTable());
                    existing.setPayload(documentsynchronize.getPayload());
                    existing.setSynchronize(documentsynchronize.getSynchronize());
                    existing.setSincronizeTypeENUM(documentsynchronize.getSincronizeTypeENUM());
                    return documentSincronizeRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("DocumentSincronize não encontrado com id: " + id));
    }

    public void delete(UUID id) {
        documentSincronizeRepository.deleteById(id);
    }
}
