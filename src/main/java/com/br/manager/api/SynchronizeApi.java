package com.br.manager.api;

import com.br.manager.domain.synchronize.dto.SincronizeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SynchronizeApi {
    private final WebClient webClient;

    public SynchronizeApi(WebClient webClient) {
        this.webClient = webClient;
    }

    public void update(SincronizeDTO sincronizeDTO) {
        webClient.put()
                .uri("/sincronize")
                .bodyValue(sincronizeDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(err -> System.err.println("Error updating synchronize: " + err.getMessage()))
                .onErrorResume(ex -> Mono.empty())
                .block();
    }
}
