package com.br.manager.domain.synchronize.service;

import com.br.manager.domain.synchronize.entity.DocumentSynchronize;
import com.br.manager.domain.synchronize.entity.MessageErrorSynchronize;
import com.br.manager.domain.synchronize.repository.MessageErrorSynchronizeRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageErrorSynchronizeServiceImpl implements MessageErrorSynchronizeService {

    private final MessageErrorSynchronizeRepository repository;

    public MessageErrorSynchronizeServiceImpl(MessageErrorSynchronizeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MessageErrorSynchronize save(MessageErrorSynchronize messageError) {
        return repository.saveAndFlush(messageError);
    }

    @Override
    public MessageErrorSynchronize buildMessageErrorSynchronize(String message, DocumentSynchronize documentSynchronize) {
        MessageErrorSynchronize messageError = new MessageErrorSynchronize();
        messageError.setMessage(message);
        if(documentSynchronize != null) {
            messageError.setDocumentSynchronize(documentSynchronize);
        }
        return messageError;
    }

}
