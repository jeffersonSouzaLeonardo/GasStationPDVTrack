package com.br.manager.service;

import com.br.manager.dto.FuelingDTO;
import com.br.manager.integration.horustech.HorustechClient;
import com.br.manager.listener.FuelingListener;
import com.br.manager.model.ConnectionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConcentratorServiceImpl implements ConcentratorService {

    private static final Logger log = LoggerFactory.getLogger(ConcentratorServiceImpl.class);

    private final HorustechClient client;
    private final FuelingListener fuelingListener;

    private volatile ConnectionStatus status = ConnectionStatus.DISCONNECTED;

    public ConcentratorServiceImpl(HorustechClient client, FuelingListener fuelingListener) {
        this.client = client;
        this.fuelingListener = fuelingListener;
        this.fuelingListener.addObserver(this::save);
    }

    @Override
    public synchronized void connect() {
        status = ConnectionStatus.CONNECTING;
        try {
            client.open();
            fuelingListener.start();
            status = ConnectionStatus.CONNECTED;
        } catch (RuntimeException e) {
            status = ConnectionStatus.DISCONNECTED;
            log.error("Failed to connect to the concentrator", e);
        }
    }

    @Override
    public synchronized void disconnect() {
        fuelingListener.stop();
        client.close();
        status = ConnectionStatus.DISCONNECTED;
    }

    @Override
    public ConnectionStatus getStatus() {
        return status;
    }

    @Override
    public void save(FuelingDTO fueling) {
        // TODO persist the fueling in the database
        log.info("Fueling to be saved: {}", fueling);
    }
}
