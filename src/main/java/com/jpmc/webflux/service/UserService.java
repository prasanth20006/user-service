package com.jpmc.webflux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jpmc.webflux.dto.UserDocumentDto;
import com.jpmc.webflux.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserDto> getAllUsers();

    Mono<UserDto> getUser(Long userId);

    Mono<UserDto> createUser(UserDto userDto);

    Mono<UserDocumentDto> sendUserDocumentDto(UserDocumentDto userDocumentDto);


}
