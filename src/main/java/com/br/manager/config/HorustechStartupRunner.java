package com.br.manager.config;

import com.br.manager.domain.integration.service.ConcentratorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;

/** Opens the concentrator connection on startup when horustech.enabled is true. */
@Component
public class HorustechStartupRunner implements ApplicationRunner {

    private final HorustechProperties properties;
    private final ConcentratorService concentratorService;

    public HorustechStartupRunner(HorustechProperties properties, ConcentratorService concentratorService) {
        this.properties = properties;
        this.concentratorService = concentratorService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (properties.isEnabled()) {
            concentratorService.connect();
        }
    }

    @PreDestroy
    void shutdown() {
        concentratorService.disconnect();
    }
}
