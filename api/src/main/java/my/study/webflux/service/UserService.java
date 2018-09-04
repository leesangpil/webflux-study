package my.study.webflux.service;

import lombok.RequiredArgsConstructor;
import my.study.webflux.domain.User;
import my.study.webflux.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by leesangpil on 2018. 9. 3..
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findOne(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> createUser(String nickname) {
        return userRepository.save(User.create(nickname));
    }

    public Mono<User> updateNickname(String id, String nickname) {
        return userRepository.findById(id).flatMap(user -> {
            user.setNickname(nickname);
            return userRepository.save(user);
        });
    }

    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }
}
