package com.br.manager.domain.synchronize.service;

import com.br.manager.domain.synchronize.entity.DocumentSynchronize;
import com.br.manager.domain.synchronize.entity.MessageErrorSynchronize;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageErrorSynchronizeService {

    MessageErrorSynchronize save(MessageErrorSynchronize messageError);

    MessageErrorSynchronize buildMessageErrorSynchronize(String message, DocumentSynchronize documentSynchronize);
}
