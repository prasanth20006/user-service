package com.jpmc.webflux.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoServiceTest {

    FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

    @Test
    void testFlux(){
        var nameFlux = fluxAndMonoService.namesFlux();
        StepVerifier.create(nameFlux)
                .expectNext("ONE").expectNextCount(2).verifyComplete();
    }
}
