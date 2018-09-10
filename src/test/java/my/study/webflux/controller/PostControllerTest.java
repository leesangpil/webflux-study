package my.study.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import my.study.webflux.domain.Post;
import my.study.webflux.service.PostService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@RunWith(SpringRunner.class)
@WebFluxTest(PostController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@Slf4j
public class PostControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    PostService postService;

    @Test
    public void requestFindAllTest(){
        when(postService.findAll(0)).thenReturn(Flux.just(createDummyPost(), createDummyPost()));
        webTestClient.get().uri("/posts/{pageNumber}", 0)
                .exchange()
                .expectStatus().isOk().expectBodyList(Post.class).hasSize(2)
                .consumeWith(document("getAllPost"));
    }

    @Test
    public void requestFindByCreatorTest(){
        when(postService.findByCreator("stonegyu", 0)).thenReturn(Flux.just(createDummyPost(), createDummyPost()));
        webTestClient.get().uri("/posts/creator/{creator}/{pageNumber}", "stonegyu",0)
                .exchange()
                .expectStatus().isOk().expectBodyList(Post.class)
                .consumeWith(p->{
                    p.getResponseBody().stream().forEach(iter-> {
                        log.info(iter.toString());
                        Assert.assertThat(iter.getCreator(), CoreMatchers.is("stonegyu"));
                    });
                })
                .consumeWith(document("getFindByCreator"));

    }

    @Test
    public void requestFindByTitleLikeTest(){
        when(postService.findByTitleLike("df",0)).thenReturn(Flux.just(createDummyPost(), createDummyPost()));
        webTestClient.get().uri("/posts/title/{title}/{pageNumber}","df",0)
                .exchange()
                .expectStatus().isOk().expectBodyList(Post.class)
                .consumeWith(p->{
                    p.getResponseBody().stream().forEach(iter ->{
                        log.info(iter.toString());
                        Assert.assertTrue(iter.getTitle().contains("dsf"));
                    });
                })
                .consumeWith(document("getFindByTitleLike"));
    }
    @Test
    public void requestAddPostTest(){
        Post addPost = createDummyPost();
        when(postService.insertPost(addPost)).thenReturn(Mono.just(addPost));
        webTestClient.post().uri("/posts/").body(Mono.just(addPost), Post.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Post.class)
                .consumeWith(p->{
                    Post result = p.getResponseBody();
                    Assert.assertTrue(addPost.equals(result));
                })
                .consumeWith(document("createPost"));
    }

    @Test
    public void requestUpdatePostTest(){
        Post post = createDummyPost();
        post.setContent("fds");
        when(postService.updatePost(post)).thenReturn(Mono.just(post));

        webTestClient.patch().uri("/posts/").body(Mono.just(post), Post.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Post.class)
                .consumeWith(p->{
                    Post result = p.getResponseBody();
                    Assert.assertTrue(post.equals(result));
                })
                .consumeWith(document("updatePost"));
    }

    Post createDummyPost(){
        return Post.createPost("fdsfsd", "fdsfsdfdsf", "stonegyu");
    }
}
