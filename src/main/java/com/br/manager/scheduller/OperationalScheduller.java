package com.br.manager.scheduller;

import com.br.manager.domain.operational.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class OperationalScheduller {

    @Autowired
    private UserService userService;

    @Scheduled( fixedDelay = 30000)
    public void fetchUsers() {
        log.info("Busca de usuários iniciada...");
        userService.synchronize();
    }

    @Scheduled( fixedDelay = 20000)
    public void persistUsers() {
        log.info("Persistência de usuários iniciada...");
        userService.persistUsers();
    }


}

