package com.br.manager.api;

import com.br.manager.domain.operational.dto.UserRequestDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class UserApi {
    private final WebClient webClient;

    public UserApi(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<UserRequestDTO> fetchUsers() {
        var responseMono = webClient.get()
                .uri("/user-synchronize")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserRequestDTO>>() {})
                .timeout(Duration.ofSeconds(5))
                .doOnNext(list -> System.out.println("Fetched " + list.size() + " users"))
                .doOnError(err -> System.err.println("Error fetching users: " + err.getMessage()))
                .onErrorResume(ex -> Mono.empty());

        return responseMono.blockOptional().orElse(List.of());
    }
}
