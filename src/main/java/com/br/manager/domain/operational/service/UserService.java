package com.br.manager.domain.operational.service;

import com.br.manager.domain.operational.mapper.UserMapper;
import com.br.manager.domain.operational.dto.UserRequestDTO;
import com.br.manager.domain.operational.repository.UserRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class UserService {

    private final WebClient webClient;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(WebClient.Builder webClientBuilder, UserRepository userRepository, UserMapper userMapper) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void fetchUsers() {
        var responseMono = webClient.get()
                .uri("/user")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserRequestDTO>>() {})
                .timeout(Duration.ofSeconds(5))
                .doOnNext(list -> System.out.println("Fetched " + list.size() + " users"))
                .doOnError(err -> System.err.println("Error fetching users: " + err.getMessage()))
                .onErrorResume(ex -> Mono.empty());

        List<UserRequestDTO> listRequest = responseMono.blockOptional().orElse(List.of());

        userRepository.saveAllAndFlush(userMapper.toEntityList(listRequest));

    }


}
