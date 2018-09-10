package my.study.webflux.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.study.webflux.domain.Post;
import my.study.webflux.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PostService {
    private final int PAGE_SIZE = 10;

    @Autowired
    private PostRepository postRepository;

    public Flux<Post> findAll(int pageNumber){
        //return Flux.empty();
        return postRepository.findAllByDeleteIsFalse(PageRequest.of(pageNumber, PAGE_SIZE));
    }
    public Flux<Post> findByCreator(String creator, int pageNumber){
        return postRepository.findByCreatorAndDeleteIsFalse(creator, PageRequest.of(pageNumber, PAGE_SIZE));
    }
    public Flux<Post> findByTitleLike(String title, int pageNumber){
        return postRepository.findByTitleLike(title, PageRequest.of(pageNumber, PAGE_SIZE));
    }
    public Mono<Post> insertPost(Post post){
        return postRepository.save(post);
    }
    public Mono<Post> updatePost(Post post){
        return postRepository.findByIdAndDeleteIsFalse(post.getId())
                .doOnSuccess(p -> {
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());
                    p.setOpen(post.getOpen());
                    p.setDelete(post.getDelete());
                    postRepository.save(p);
                })
                .doOnError( e->{
                    log.info(e.getMessage());
                });
                //.onErrorReturn(Mono<Void>);
    }

}
