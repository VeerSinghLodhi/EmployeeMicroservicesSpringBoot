package com.example.API_GATEWAY.contoller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {


    @GetMapping("/employeeFallback")
    public Mono<?> employeeFallback() {
        return Mono.just("Employee service is not available right now. try again later.");
    }

    @GetMapping("/addressFallback")
    public Mono<?> addressFallback() {
        return Mono.just("Address service is not available right now. try again later.");
    }
}
