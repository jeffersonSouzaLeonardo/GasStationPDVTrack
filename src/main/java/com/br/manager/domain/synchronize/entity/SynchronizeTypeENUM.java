package com.br.manager.domain.synchronize.entity;

public enum SynchronizeTypeENUM {
    RECEIPT,
    SEND;

    public static final SynchronizeTypeENUM DEFAULT = RECEIPT;

    public static SynchronizeTypeENUM getDefault() {
        return DEFAULT;
    }
}
