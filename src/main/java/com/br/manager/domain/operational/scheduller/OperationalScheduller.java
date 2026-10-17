package com.br.manager.domain.operational.scheduller;

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

    @Scheduled( fixedDelay = 3000)
    public void executeOperationalTasks() {
        log.info("Executing operational tasks...");
        userService.fetchUsers();
    }
}

