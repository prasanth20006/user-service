package com.jpmc.webflux.dao;

import com.jpmc.webflux.dto.UserDocumentDto;
import com.jpmc.webflux.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserDao {
    Flux<UserDto> getAllUsers();

    Mono<UserDto> getUser(Long userId);

    Mono<UserDto> createUser(UserDto userDto);

    Mono<UserDocumentDto> createUserDocument(UserDocumentDto userDocumentDto);
}
