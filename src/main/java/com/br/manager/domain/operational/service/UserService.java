package com.br.manager.domain.operational.service;

import com.br.manager.api.UserApi;
import com.br.manager.domain.operational.dto.UserRequestDTO;
import com.br.manager.domain.operational.entity.User;
import com.br.manager.domain.operational.mapper.UserMapper;
import com.br.manager.domain.operational.repository.UserRepository;
import com.br.manager.domain.synchronize.entity.DocumentSynchronize;
import com.br.manager.domain.synchronize.entity.SynchronizeTypeENUM;
import com.br.manager.domain.synchronize.service.DocumentSynchronizeService;
import com.br.manager.domain.synchronize.service.MessageErrorSynchronizeService;
import com.br.manager.domain.synchronize.util.JsonUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserApi userApi;
    private final DocumentSynchronizeService documentSynchronizeService;
    private final MessageErrorSynchronizeService messageErrorSynchronizeService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserApi userApi, DocumentSynchronizeService documentSynchronizeService, MessageErrorSynchronizeService messageErrorSynchronizeService, UserMapper userMapper, UserRepository userRepository){
        this.userApi = userApi;
        this.documentSynchronizeService = documentSynchronizeService;
        this.messageErrorSynchronizeService = messageErrorSynchronizeService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public void synchronize() {
        try {
            if( documentSynchronizeService.findBySynchronizeFalse().isEmpty() ) {
                List<UserRequestDTO> usersDTO = userApi.fetchUsers();
                if (!usersDTO.isEmpty()) {
                    documentSynchronizeService.save("user", JsonUtil.toJson(usersDTO), SynchronizeTypeENUM.RECEIPT);
                }
            }
        } catch (Exception e) {
            messageErrorSynchronizeService.save(
                    messageErrorSynchronizeService.buildMessageErrorSynchronize(e.getMessage(), null));
        }
    }

    @Transactional
    public void persistUsers(){
        List<DocumentSynchronize> docs = documentSynchronizeService.findBySynchronizeFalse();
        for (DocumentSynchronize doc : docs) {
            try {
            List<UserRequestDTO> usersDTO = JsonUtil.fromJsonList(doc.getPayload(), UserRequestDTO.class);

            List<UserRequestDTO> usersToDelete = usersDTO.stream()
                    .filter(userDTO -> userDTO.getDataExclusao() != null)
                    .collect(Collectors.toList());

            List<UserRequestDTO> usersToInclude = usersDTO.stream()
                    .filter(userDTO -> userDTO.getDataExclusao() == null)
                    .collect(Collectors.toList());

            if (!usersToInclude.isEmpty()) {
                this.save(usersToInclude);
            }

            if (!usersToDelete.isEmpty()) {
                this.delete(usersToDelete);
            }

            System.out.println("Persistindo documento users: " + doc.getId());
            doc.setSynchronize(true);
            documentSynchronizeService.update(doc.getId(), doc);
            documentSynchronizeService.save("user", JsonUtil.toJson(usersDTO), SynchronizeTypeENUM.RECEIPT);
            } catch (Exception e) {
                messageErrorSynchronizeService.save(
                        messageErrorSynchronizeService.buildMessageErrorSynchronize(e.getMessage(), doc));
            }
        }
    }

    private void delete(List<UserRequestDTO> usersToDelete) {
        List<UUID> idsToDelete = usersToDelete.stream()
                .map(UserRequestDTO::getId)
                .collect(Collectors.toList());
        userRepository.deleteAllById(idsToDelete);
    }

    public void save(List<UserRequestDTO> usersDTO){
        List<User> users = userMapper.toEntityList(usersDTO);
        userRepository.saveAllAndFlush(users);
    }


}
