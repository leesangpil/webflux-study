package my.study.webflux.controller;

import lombok.RequiredArgsConstructor;
import my.study.webflux.domain.User;
import my.study.webflux.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by leesangpil on 2018. 9. 3..
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public Flux<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return userService.findOne(id);
    }

    @PostMapping("/{nickname}")
    public Mono<User> createUser(@PathVariable String nickname) {
        return userService.createUser(nickname);
    }

    @PatchMapping("/{id}/{nickname}")
    public Mono<User> updateNickname(
            @PathVariable String id,
            @PathVariable String nickname) {
        return userService.updateNickname(id, nickname);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

}
