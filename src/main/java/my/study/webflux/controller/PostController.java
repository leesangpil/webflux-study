package my.study.webflux.controller;

import lombok.AllArgsConstructor;
import lombok.Value;
import my.study.webflux.domain.Post;
import my.study.webflux.service.PostService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{pageNumber}")
    public Flux<Post> index(@PathVariable int pageNumber){
        return postService.findAll(pageNumber);
    }

    @GetMapping("/creator/{creator}/{pageNumber}")
    public Flux<Post> findByCreator(@NotBlank @PathVariable String creator, @PathVariable int pageNumber){
        return postService.findByCreator(creator, pageNumber);
    }

    @GetMapping("/title/{title}/{pageNumber}")
    public Flux<Post> findByTitleLike(@NotBlank @PathVariable String title, @PathVariable int pageNumber){
        return postService.findByTitleLike(title, pageNumber);
    }

    @PostMapping("/")
    public Mono<Post> insert(@RequestBody @Valid Post post){
        return postService.insertPost(post);
    }

    @PatchMapping("/")
    public Mono<Post> update(@RequestBody @Valid Post post){
        return postService.updatePost(post);
    }
}
