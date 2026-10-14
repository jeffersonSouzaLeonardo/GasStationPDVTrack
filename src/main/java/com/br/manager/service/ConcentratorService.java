package com.br.manager.service;

import com.br.manager.dto.FuelingDTO;
import com.br.manager.model.ConnectionStatus;

public interface ConcentratorService {

    /** Opens the connection with the pump concentrator and starts listening to fuelings. Blocking call. */
    void connect();

    void disconnect();

    ConnectionStatus getStatus();

    /** Receives a fueling read from the concentrator and persists it. */
    void save(FuelingDTO fueling);
}
