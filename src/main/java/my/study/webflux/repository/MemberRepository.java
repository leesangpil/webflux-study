package my.study.webflux.repository;

import my.study.webflux.domain.Member;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
public interface MemberRepository extends ReactiveMongoRepository<Member, String> {
    Mono<Member> findByName(String name);
}
