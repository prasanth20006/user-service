package com.jpmc.webflux.handler;

import com.jpmc.webflux.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.jpmc.webflux.config.router.UserRouterConfig.CREATE_USER_URL;
import static com.jpmc.webflux.config.router.UserRouterConfig.GET_USER_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserHandlerApiTest {

    public static final String BASE_URL = "http://localhost:8080";

    WebClient webClient;

    @BeforeEach
    void setUp(){
        webClient = WebClient.builder().baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().wiretap(true)))
                .build();
    }

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testGetUserById() throws InterruptedException{

       // CountDownLatch countDownLatch = new CountDownLatch(1);

        webTestClient.get().uri(GET_USER_URL+"/"+1)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.city").isNotEmpty()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.email").isNotEmpty();

/*
       Mono<UserDto> userDtoMono = webClient.get().uri(GET_USER_URL+"/"+1)
                .accept(APPLICATION_JSON)
                .retrieve().bodyToMono(UserDto.class);
        userDtoMono.subscribe(
                userDto -> {
                    System.out.println("*******************************************************************************************");
                    System.out.println(userDto.getName()+":"+userDto.getId()    );
                    System.out.println("*******************************************************************************************");
                    assertThat(userDto).isNotNull();
                   // assertThat(userDto.getName()).isNotNull();
                    countDownLatch.countDown();
                });*/

       // countDownLatch.await(2000, TimeUnit.MILLISECONDS);
       // assertThat(countDownLatch.getCount()).isEqualTo(0);

    }

    @Test
    public void testCreateUser() throws InterruptedException{

        UserDto userDto = gerUserDetails(1);

       // CountDownLatch countDownLatch = new CountDownLatch(1);

                webTestClient.post().uri(CREATE_USER_URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .body(Mono.just(userDto), UserDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.city").isEqualTo(userDto.getCity())
                .jsonPath("$.name").isEqualTo(userDto.getName())
                .jsonPath("$.email").isEqualTo(userDto.getEmail());




    }

    private UserDto gerUserDetails(int i) {
        UserDto userDto = new UserDto();
        userDto.setName("Name"+i);
        userDto.setCity("city"+i);
        userDto.setState("State"+i);
        userDto.setCreatedBy(1l);
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setUpdatedBy(1l);
        userDto.setUpdatedAt(LocalDateTime.now());
        userDto.setDateOfBirth(LocalDate.now());
        return userDto;
    }


}
