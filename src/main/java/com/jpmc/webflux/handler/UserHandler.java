package com.jpmc.webflux.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jpmc.webflux.dto.UserDocumentDto;
import com.jpmc.webflux.dto.UserDto;
import com.jpmc.webflux.service.UserService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    @Autowired
    private UserService userService;



    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {



        Long userId = Long.valueOf(serverRequest.pathVariable("id"));
        return userService.getUser(userId).flatMap(
                userDto -> {
                    return ServerResponse.ok().bodyValue(userDto);
                }
        ).//switchIfEmpty(ServerResponse.notFound().build()).log();

         switchIfEmpty(ServerResponse.ok().bodyValue(new UserDto())).log();
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {

        Mono<UserDto> userDtoMono = serverRequest.bodyToMono(UserDto.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return  userDtoMono
                .flatMap(userService::createUser)
                .flatMap(userDto -> {
                    return  ServerResponse.status(HttpStatus.CREATED).bodyValue(userDto);
                })
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> sendUserDocumentDetails(ServerRequest serverRequest) {

        Mono<UserDocumentDto> userDocumentDtoMono = serverRequest.bodyToMono(UserDocumentDto.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return  userDocumentDtoMono
                .flatMap(userService::sendUserDocumentDto)
                .flatMap(userdocDto -> {
                    return  ServerResponse.status(HttpStatus.CREATED).bodyValue(userdocDto);
                })
                .switchIfEmpty(notFound);
    }

}
