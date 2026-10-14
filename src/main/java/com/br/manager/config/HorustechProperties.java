package com.br.manager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "horustech")
public class HorustechProperties {

    private boolean enabled = false;
    private String host = "192.168.0.250";
    private int port = 2001;
    private int connectTimeoutMs = 3000;
    private int readTimeoutMs = 3000;
    private long pollingIntervalMs = 500;

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public int getConnectTimeoutMs() { return connectTimeoutMs; }
    public void setConnectTimeoutMs(int connectTimeoutMs) { this.connectTimeoutMs = connectTimeoutMs; }

    public int getReadTimeoutMs() { return readTimeoutMs; }
    public void setReadTimeoutMs(int readTimeoutMs) { this.readTimeoutMs = readTimeoutMs; }

    public long getPollingIntervalMs() { return pollingIntervalMs; }
    public void setPollingIntervalMs(long pollingIntervalMs) { this.pollingIntervalMs = pollingIntervalMs; }
}
