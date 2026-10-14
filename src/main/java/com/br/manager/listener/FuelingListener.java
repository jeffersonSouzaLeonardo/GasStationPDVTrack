package com.br.manager.listener;

import com.br.manager.config.HorustechProperties;
import com.br.manager.dto.FuelingDTO;
import com.br.manager.integration.horustech.FuelingParser;
import com.br.manager.integration.horustech.HorustechClient;
import com.br.manager.integration.horustech.HorustechException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** Polls the concentrator for finished fuelings and stores them in memory. */
@Component
public class FuelingListener {

    private static final Logger log = LoggerFactory.getLogger(FuelingListener.class);

    private final HorustechClient horustechClient;
    private final FuelingParser fuelingParser;
    private final HorustechProperties horustechProperties;
    private final List<FuelingDTO> fuelings = new CopyOnWriteArrayList<>();
    private final List<FuelingObserver> fuelingObservers = new CopyOnWriteArrayList<>();

    private ScheduledExecutorService executor;

    public FuelingListener(HorustechClient horustechClient, FuelingParser fuelingParser, HorustechProperties horustechProperties) {
        this.horustechClient = horustechClient;
        this.fuelingParser = fuelingParser;
        this.horustechProperties = horustechProperties;
    }

    public synchronized void start() {
        if (executor != null) {
            return;
        }
        executor = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "horustech-fueling-listener");
            thread.setDaemon(true);
            return thread;
        });
        executor.scheduleWithFixedDelay(this::poll, 0, horustechProperties.getPollingIntervalMs(), TimeUnit.MILLISECONDS);
    }

    public synchronized void stop() {
        if (executor != null) {
            executor.shutdownNow();
            executor = null;
        }
    }

    public void addObserver(FuelingObserver observer) {
        fuelingObservers.add(observer);
    }

    public void removeObserver(FuelingObserver observer) {
        fuelingObservers.remove(observer);
    }

    private void poll() {
        // Any Throwable would silently cancel the scheduled task, so everything is caught here.
        try {
            while (true) {
                String response;
                Optional<FuelingDTO> fueling;
                try {
                    response = horustechClient.readFueling();
                    log.debug("Fueling read answer: '{}'", response);
                    fueling = fuelingParser.parse(response);
                } catch (HorustechException e) {
                    // Skip the unreadable record, otherwise the read pointer would be stuck on it forever
                    log.error("Discarding invalid fueling record: {}", e);
                    horustechClient.nextFueling();
                    continue;
                }
                if (fueling.isEmpty()) {
                    return;
                }
                horustechClient.nextFueling();
                log.info("Fueling received: {}", fueling.get());
                notifyObservers(fueling.get());
            }
        } catch (Throwable e) {
            log.error("Failed to read fuelings from the concentrator", e);
            return;
        }
    }

    private void notifyObservers(FuelingDTO fueling) {
        for (FuelingObserver observer : fuelingObservers) {
            try {
                observer.onFueling(fueling);
            } catch (RuntimeException e) {
                log.error("Fueling observer failed", e);
            }
        }
    }
}
