package my.study.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public Mono<String> index() {
        return Mono.just("Hello WebFlux");
    }
}
