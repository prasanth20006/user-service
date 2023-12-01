package com.jpmc.webflux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoService {


    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("One","Two","Three"))
                .filter(s ->s!=null)
                .map(String::toUpperCase);
    }

    public Mono<String> nameMomno(){
        return Mono.just("Alex");
    }

    /*public static void main(String[] args) {

        // example of flux subscription
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        fluxAndMonoService.namesFlux().subscribe(s -> {
            System.out.println("name is "+s);
        } );

        fluxAndMonoService.nameMomno().subscribe(s-> System.out.println("Mono name is "+s));
    }*/
}
