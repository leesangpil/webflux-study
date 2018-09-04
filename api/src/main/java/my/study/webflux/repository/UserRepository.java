package my.study.webflux.repository;

import my.study.webflux.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by leesangpil on 2018. 9. 2..
 */
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
