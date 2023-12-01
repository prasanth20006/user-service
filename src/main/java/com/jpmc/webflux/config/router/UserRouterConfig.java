package com.jpmc.webflux.config.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jpmc.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UserRouterConfig {

    public static final String GET_USER_URL = "/api/users";

    public static final String GET_USER_BY_ID_URL = "/api/users/{id}";

    public static final String CREATE_USER_URL = "/api/users";

    public static final String CREATE_USER_DOCUMENT_URL = "/api/users/user-documents";

    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) throws JsonProcessingException {

        return route().GET(GET_USER_BY_ID_URL, accept(APPLICATION_JSON),userHandler::getUserById)
                .POST(CREATE_USER_URL,userHandler::createUser)
                .POST(CREATE_USER_DOCUMENT_URL,userHandler::sendUserDocumentDetails)
                .build();
    }
}
