package com.br.manager.domain.integration.horustech;

public class HorustechException extends RuntimeException {

    public HorustechException(String message) {
        super(message);
    }

    public HorustechException(String message, Throwable cause) {
        super(message, cause);
    }
}
