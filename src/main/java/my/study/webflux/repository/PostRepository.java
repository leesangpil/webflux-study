package my.study.webflux.repository;

import my.study.webflux.domain.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;


public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findAllByDeleteIsFalse(Pageable pageable);
    Flux<Post> findByCreatorAndDeleteIsFalse(String creator, Pageable pageable);

    Mono<Post> findByIdAndDeleteIsFalse(String id);

    Flux<Post> findByTitleLike(String title, Pageable pageable);
}
