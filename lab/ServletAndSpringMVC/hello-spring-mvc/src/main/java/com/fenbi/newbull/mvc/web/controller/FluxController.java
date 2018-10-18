package com.fenbi.newbull.mvc.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

@Slf4j
@RestController
public class FluxController {

    @GetMapping("/mono")
    public Mono<String> mono() {
        return Mono.just("mono");
    }

    @GetMapping("/flux")
    public Flux<String> flux() {
        Random rand = new Random();
        Flux<Integer> bids = Flux.interval(Duration.ofMillis(500)).map(i -> rand.nextInt(10000));
        Flux<Integer> asks = Flux.interval(Duration.ofMillis(700)).map(i -> rand.nextInt(10000));
        return Flux.combineLatest(bids, asks, (a, b) -> calc(a, b)).take(1000);
    }

    private String calc(int bid, int ask) {
        boolean deal = bid >= ask;
        String msg = Instant.now() + String.format(" bid=%d, ask=%d", bid, ask);
        if (deal) {
            msg += " deal";
        }
        return msg;
    }

}